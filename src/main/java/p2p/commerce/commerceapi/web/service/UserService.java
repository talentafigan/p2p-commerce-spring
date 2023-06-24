package p2p.commerce.commerceapi.web.service;


import p2p.commerce.commerceapi.web.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest);
    String logout(HttpServletRequest request);
    ProfileResponse profileMe();
}

