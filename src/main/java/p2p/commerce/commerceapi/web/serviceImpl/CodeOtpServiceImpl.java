package p2p.commerce.commerceapi.web.serviceImpl;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.EmailConfig;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.dto.ChangePasswordRequest;
import p2p.commerce.commerceapi.web.dto.CheckOtpRequest;
import p2p.commerce.commerceapi.web.dto.CodeOtpRequest;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.CodeOtpService;
import p2p.commerce.commerceapi.web.service.UserService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class CodeOtpServiceImpl extends EmailConfig implements CodeOtpService {

    @Value("${url.app.web_seller}")
    private String webSeller;

    @Value("${url.app.web_client}")
    private String webClient;

    private CodeOtpRespository codeOtpRespository;
    private SellesRepository sellesRepository;
    private UserTypeRepository userTypeRepository;
    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;
    private static final int LIMITED = 6;

    @Autowired
    public CodeOtpServiceImpl(CodeOtpRespository codeOtpRespository, SellesRepository sellesRepository, ClientRepository clientRepository,UserTypeRepository userTypeRepository, PasswordEncoder passwordEncoder) {
        this.codeOtpRespository = codeOtpRespository;
        this.sellesRepository = sellesRepository;
        this.clientRepository = clientRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public CodeOtp createToken(CodeOtpRequest codeOtpRequest) throws TemplateException, MessagingException, IOException {
        UserType type = userTypeRepository.findById(codeOtpRequest.getUserType()).orElseThrow(() -> new BussinesException("USER TYPE NOT FOUND"));
        Users user;
        if (type.getUserTypeName().equals("Seller")) {
            var sellerData = sellesRepository.findByEmail(codeOtpRequest.getEmail()).orElseThrow(() -> new BussinesException("EMAIL IS NOT REGISTERED IN THIS APPLICATION"));
            user = sellerData.getUser();

        } else if (type.getUserTypeName().equals("Client")) {
            var clientData = clientRepository.findByEmail(codeOtpRequest.getEmail()).orElseThrow(() -> new BussinesException("EMAIL IS NOT REGISTERED IN THIS APPLICATION"));
            user = clientData.getUser();
        } else {
            throw new BussinesException("Can't change password in admin role");
        }
        var alreadyCode = codeOtpRespository.findByUserAndUsedIsFalse(user);
        if (alreadyCode.isPresent()) {
            sendingMailOtp(codeOtpRequest.getEmail(), objTemplate(user.getUsername(), alreadyCode.get().getCode(), user.getUserType().getUserTypeName()), user.getUserType().getUserTypeName());
            return alreadyCode.get();
        }
        while (true) {
            String codeOtp = createTokenRandom();
            if (codeOtpRespository.findByCodeAndUsedIsFalse(codeOtp).isPresent()) continue;
            objTemplate(user.getUsername(), codeOtp, user.getUserType().getUserTypeName());
            sendingMailOtp(codeOtpRequest.getEmail(), objTemplate(user.getUsername(), codeOtp, user.getUserType().getUserTypeName()), user.getUserType().getUserTypeName());
            return codeOtpRespository.save(CodeOtp.builder().code(codeOtp).used(false).user(user).build());
        }

    }


    @Transactional(readOnly = true)
    @Override
    public String checkActive(CheckOtpRequest checkOtpRequest) {
        CodeOtp resp=  codeOtpRespository.findByCode(checkOtpRequest.getCode()).orElseThrow(() -> new BussinesException("Code OTP not found"));
        if (resp.isUsed()) return "In Active";
        return "Active";
    }

    @Transactional
    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        CodeOtp resp =  codeOtpRespository.findByCodeAndUsedIsFalse(changePasswordRequest.getCode()).orElseThrow(() -> new BussinesException("Code OTP not found"));
        Users user =resp.getUser();
        if (user.getUserType().getUserTypeName().equals("Seller")) {
            Sellers sellerData = sellesRepository.findByUser(user);
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            sellerData.setPassword(user.getPassword());
        } else {
            Clients clients = clientRepository.findByUser(user);
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            clients.setPassword(user.getPassword());
        }
        resp.setUsed(true);
        return "PASSWORD SUCCESSFULLY CHANGED";
    }

    private String createTokenRandom() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < LIMITED) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    private Map<String, Object> objTemplate(String name, String otp, String typeUser) {
        Map<String, Object> template = new HashMap<>();
        template.put("firstName", name);
        String link = "";
        if (typeUser.equals("Seller")) {
            link = webSeller;
        } else {
            link = webClient;
        }
        template.put("link", link + "auth/reset-password?token=" + otp);

        return template;
    }
}
