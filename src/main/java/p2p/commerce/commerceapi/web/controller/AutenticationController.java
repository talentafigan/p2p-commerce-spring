package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.LoginRequest;
import p2p.commerce.commerceapi.web.dto.RegisterRequest;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;
import p2p.commerce.commerceapi.web.service.UserService;

@RestController
@RequestMapping("/api")
public class AutenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResponse<UserAuthenticationLog> login(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public CommonResponse<UserAuthenticationLog> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseHelper.ok(userService.register(registerRequest));
    }

}
