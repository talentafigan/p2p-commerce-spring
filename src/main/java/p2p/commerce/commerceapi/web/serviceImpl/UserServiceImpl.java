package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.UserDetailsServiceImpl;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.configuration.init_token.TokenProvider;
import p2p.commerce.commerceapi.web.dto.LoginRequest;
import p2p.commerce.commerceapi.web.dto.RegisterRequest;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.UserService;

@Slf4j
@Service
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

    @Autowired
    public UserServiceImpl(ClientRepository clientRepository, AdminRepository adminRepository, SellesRepository sellesRepository, UserRepository userRepository, UserAuthenticationLogRepository userAuthenticationLogRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserTypeRepository userTypeRepository, UserDetailsServiceImpl userDetailsService, StatusRepository statusRepository, TokenProvider tokenProvider) {
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.sellesRepository = sellesRepository;
        this.userRepository = userRepository;
        this.userAuthenticationLogRepository = userAuthenticationLogRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userTypeRepository = userTypeRepository;
        this.userDetailsService = userDetailsService;
        this.statusRepository = statusRepository;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    @Override
    public UserAuthenticationLog login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getKey(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("BAD CREDENTIALS");
        }
        loginRequest.setKey(loginRequest.getKey());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getKey());
        Users user = userRepository.findByUsername(loginRequest.getKey()).get();
        if (user.getUserType().getUserTypeId() != loginRequest.getUserTypeId()) throw new BussinesException("ACCESS REJECTED");
        return tokenProvider.generateToken(userDetails);
    }

    @Transactional
    @Override
    public UserAuthenticationLog register(RegisterRequest registerRequest) {
        UserType type = userTypeRepository.findById(registerRequest.getUserTypeId()).orElseThrow(() -> new BussinesException("USER TYPE ID NOT FOUND"));
        Users usersResp = new Users();
        usersResp.setUserType(type);
        usersResp = userRepository.save(usersResp);
        if (type.getUserTypeName().equals("Admin")) {
            Admins admins = modelMapper.map(registerRequest, Admins.class);
            if (adminRepository.findByUsername(admins.getUsername()).isPresent()) {
                throw new BussinesException("USERNAME ALREADY EXIST");
            }
            admins.setUser(usersResp);
            admins.setPassword(passwordEncoder.encode(admins.getPassword()));
            usersResp.setUsername(admins.getUsername());
            usersResp.setPassword(admins.getPassword());
            usersResp.setStatus(statusRepository.findById(1).get());
            usersResp = userRepository.save(usersResp);
            adminRepository.save(admins);
        } else if (type.getUserTypeName().equals("Seller")) {
            Sellers sellers = modelMapper.map(registerRequest, Sellers.class);
            if (sellesRepository.findByUsername(sellers.getUsername()).isPresent()) {
                throw new BussinesException("USERNAME ALREADY EXIST");
            }
            sellers.setUser(usersResp);
            sellers.setPassword(passwordEncoder.encode(sellers.getPassword()));
            usersResp.setUsername(sellers.getUsername());
            usersResp.setPassword(sellers.getPassword());
            usersResp.setStatus(statusRepository.findById(2).get());
            usersResp = userRepository.save(usersResp);
            sellesRepository.save(sellers);
        } else {
            Clients clients = modelMapper.map(registerRequest, Clients.class);
            if (clientRepository.findByUsername(clients.getUsername()).isPresent()) {
                throw new BussinesException("USERNAME ALREADY EXIST");
            }
            clients.setUser(usersResp);
            clients.setPassword(passwordEncoder.encode(clients.getPassword()));
            usersResp.setUsername(clients.getUsername());
            usersResp.setPassword(clients.getPassword());
            usersResp.setStatus(statusRepository.findById(1).get());
            usersResp = userRepository.save(usersResp);
            clientRepository.save(clients);
        }
        return login(LoginRequest.builder().key(usersResp.getUsername()).password(registerRequest.getPassword()).userTypeId(usersResp.getUserType().getUserTypeId()).build());
    }
}
