package p2p.commerce.commerceapi.configuration.init_token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.repository.UserAuthenticationLogRepository;
import p2p.commerce.commerceapi.web.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class TokenProvider {
    private UserAuthenticationLogRepository userAuthenticationLogRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;

    @Autowired
    public TokenProvider(UserAuthenticationLogRepository userAuthenticationLogRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.userAuthenticationLogRepository = userAuthenticationLogRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }



    public UserAuthenticationLog generateToken(UserDetails userDetails) {
        Users user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new BussinesException("USERNAME NOT FOUND GENERATE TOKEN"));
        Optional<UserAuthenticationLog> token = userAuthenticationLogRepository.findByUserAndStatus(user, statusRepository.findById(1).get());
        if (token.isPresent()) {
            return token.get();
        }
        return userAuthenticationLogRepository.save(UserAuthenticationLog.builder()
                .accessToken(generateRandomToken())
                .status(statusRepository.findByStatusId(1))
                .user(user).build());
    }
    public UserAuthenticationLog getDataToken(String token) {
        return  userAuthenticationLogRepository.findByAccessTokenAndStatus(token, statusRepository.findById(1).get()).orElseThrow(() -> new BussinesException("TOKEN NOT FOUND"));
    }
    public boolean validateToken(String token) {
        UserAuthenticationLog tokenUser =  userAuthenticationLogRepository.findByAccessTokenAndStatus(token, statusRepository.findById(1).get()).orElse(null);
        if (tokenUser == null) return false;
        if (tokenUser.getCreateDate().getDate() > new Date().getDate()) {
            return  false;
        }
        return true;
    }
    private static String generateRandomToken() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        int len = 95;
        StringBuilder sb = new StringBuilder(len);
        sb.append("p2p_");
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
