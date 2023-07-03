package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.data.UserDetailsServiceImpl;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.configuration.init_token.TokenProvider;
import p2p.commerce.commerceapi.web.dto.*;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ClientRepository clientRepository;
    private AdminRepository adminRepository;
    private SellesRepository sellesRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;
    private UserAuthenticationLogRepository userAuthenticationLogRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserTypeRepository userTypeRepository;
    private UserDetailsServiceImpl userDetailsService;
    private TokenProvider tokenProvider;
    private AuthenticationFacade authenticationFacade;

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getKey(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("BAD CREDENTIALS");
        }
        loginRequest.setKey(loginRequest.getKey());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getKey());
        Users user = userRepository.findByUsernameOrEmail(loginRequest.getKey()).get();
        if (user.getUserType().getUserTypeId() != loginRequest.getUserTypeId())
            throw new BussinesException("ACCESS REJECTED");
        LoginResponse response = modelMapper.map(tokenProvider.generateToken(userDetails), LoginResponse.class);
        return response;
    }


    private Object findUserData(Users user) {
        try {
            if (user.getUserType().getUserTypeName().equals("Admin")) {
                return adminRepository.findByUser(user);
            }
            if (user.getUserType().getUserTypeName().equals("Seller")) {
                return sellesRepository.findByUser(user);
            }
            return clientRepository.findByUser(user);
        } catch (Exception e) {
            throw new BussinesException("CAN'T GET USER");
        }
    }

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        UserType type = userTypeRepository.findById(registerRequest.getUserTypeId()).orElseThrow(() -> new BussinesException("USER TYPE ID NOT FOUND"));
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new BussinesException("USERNAME ALREADY EXIST");
        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new BussinesException("EMAIL ALREADY EXIST");
        Users usersResp = new Users();
        RegisterResponse response = null;
        usersResp.setUserType(type);
        usersResp = userRepository.save(usersResp);
        if (type.getUserTypeName().equals("Admin")) {
            Admins admins = modelMapper.map(registerRequest, Admins.class);
            admins.setUser(usersResp);
            admins.setPassword(passwordEncoder.encode(admins.getPassword()));
            usersResp.setUsername(admins.getUsername());
            usersResp.setPassword(admins.getPassword());
            usersResp.setStatus(statusRepository.findById(1).get());
            usersResp = userRepository.save(usersResp);
            adminRepository.save(admins);
            response = modelMapper.map(admins, RegisterResponse.class);
            response.setUserType(usersResp.getUserType());
        } else if (type.getUserTypeName().equals("Seller")) {
            Sellers sellers = modelMapper.map(registerRequest, Sellers.class);
            if (sellesRepository.existsByPhone(sellers.getPhone())) throw new BussinesException("PHONE ALREADY EXIST");
            sellers.setUser(usersResp);
            sellers.setPassword(passwordEncoder.encode(sellers.getPassword()));
            usersResp.setUsername(sellers.getUsername());
            usersResp.setEmail(sellers.getEmail());
            usersResp.setPassword(sellers.getPassword());
            usersResp.setStatus(statusRepository.findById(2).get());
            usersResp = userRepository.save(usersResp);
            sellesRepository.save(sellers);
            response = modelMapper.map(sellers, RegisterResponse.class);
            response.setUserType(usersResp.getUserType());
        } else {
            Clients clients = modelMapper.map(registerRequest, Clients.class);
            if (clientRepository.existsByPhone(clients.getPhone())) throw new BussinesException("PHONE ALREADY EXIST");
            clients.setUser(usersResp);
            clients.setPassword(passwordEncoder.encode(clients.getPassword()));
            usersResp.setUsername(clients.getUsername());
            usersResp.setEmail(clients.getEmail());
            usersResp.setPassword(clients.getPassword());
            usersResp.setStatus(statusRepository.findById(1).get());
            usersResp = userRepository.save(usersResp);
            clientRepository.save(clients);
            response = modelMapper.map(clients, RegisterResponse.class);
            response.setUserType(usersResp.getUserType());
        }
        return response;
    }

    @Override
    public String logout(HttpServletRequest request) {
        String token = request.getHeader("x-token-id");
        UserAuthenticationLog authenticationLog = userAuthenticationLogRepository.findByAccessToken(token).orElseThrow(() -> new BussinesException("TOKEN NOT FOUND"));
        if (authenticationLog.getUser().getUserType().getUserTypeName().equals("Admin")) return "SUCCESS";
        authenticationLog.setDeleteDate(new Date());
        authenticationLog.setStatus(statusRepository.findById(3).get());
        userAuthenticationLogRepository.save(authenticationLog);
        return "SUCCESS";
    }

    @Override
    public ProfileResponse profileMe() {
        Users user = authenticationFacade.getAuthentication();
        return ProfileResponse.builder().userId(user.getUserId()).user(findUserData(user)).build();
    }

    @Transactional
    @Override
    public String changePasswordProfile(ProfileChangePasswordRequest profileChangePasswordRequest) {
        Users user = authenticationFacade.getAuthentication();
        var validPassword = passwordEncoder.matches(profileChangePasswordRequest.getOldPassword(), user.getPassword());
        if (!validPassword) throw new BussinesException("OLD PASSWORD INCORRECT");
        user.setPassword(passwordEncoder.encode(profileChangePasswordRequest.getNewPassword()));
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            Admins admin = adminRepository.findByUser(user);
            admin.setPassword(passwordEncoder.encode(profileChangePasswordRequest.getNewPassword()));
            adminRepository.save(admin);
        } else if (user.getUserType().getUserTypeName().equals("Seller")) {
            Sellers sellers = sellesRepository.findByUser(user);
            sellers.setPassword(passwordEncoder.encode(profileChangePasswordRequest.getNewPassword()));
            sellesRepository.save(sellers);
        } else {
            Clients clients = clientRepository.findByUser(user);
            clients.setPassword(passwordEncoder.encode(profileChangePasswordRequest.getNewPassword()));
            clientRepository.save(clients);
        }
        userRepository.save(user);
        return "PASSWORD CHANGED SUCCESSFULLY";
    }

    @Transactional
    @Override
    public ProfileResponse editProfile(ProfileRequest profileRequest) {
        Users user = authenticationFacade.getAuthentication();
        user.setUsername(profileRequest.getUsername());
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            Admins admin = adminRepository.findByUser(user);
            admin.setFullname(profileRequest.getFullname());
            admin.setUsername(profileRequest.getUsername());
            adminRepository.save(admin);
        } else if (user.getUserType().getUserTypeName().equals("Seller")) {
            user.setEmail(profileRequest.getEmail());
            Sellers sellers = sellesRepository.findByUser(user);
            sellers.setEmail(profileRequest.getEmail());
            sellers.setFullname(profileRequest.getFullname());
            sellers.setPhone(profileRequest.getPhone());
            sellers.setUsername(profileRequest.getUsername());
            sellesRepository.save(sellers);
        } else {
            user.setEmail(profileRequest.getEmail());
            Clients clients = clientRepository.findByUser(user);
            clients.setEmail(profileRequest.getEmail());
            clients.setFullname(profileRequest.getFullname());
            clients.setPhone(profileRequest.getPhone());
            clients.setUsername(profileRequest.getUsername());
            clientRepository.save(clients);
        }
        userRepository.save(user);
        return profileMe();
    }
}
