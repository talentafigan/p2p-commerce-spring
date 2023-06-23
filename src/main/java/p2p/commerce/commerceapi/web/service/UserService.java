package p2p.commerce.commerceapi.web.service;


import p2p.commerce.commerceapi.web.dto.LoginRequest;
import p2p.commerce.commerceapi.web.dto.RegisterRequest;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;

public interface UserService {
    UserAuthenticationLog login(LoginRequest loginRequest);
    UserAuthenticationLog register(RegisterRequest registerRequest);
}
