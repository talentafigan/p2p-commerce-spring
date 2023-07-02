package p2p.commerce.commerceapi.web.service;


import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest);
    String logout(HttpServletRequest request);
    ProfileResponse profileMe();

    @Transactional
    String changePasswordProfile(ProfileChangePasswordRequest profileChangePasswordRequest);

    @Transactional
    ProfileResponse editProfile(ProfileRequest profileRequest);
}

