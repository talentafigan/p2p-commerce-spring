package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.LoginRequest;
import p2p.commerce.commerceapi.web.dto.LoginResponse;
import p2p.commerce.commerceapi.web.dto.RegisterRequest;
import p2p.commerce.commerceapi.web.dto.RegisterResponse;
import p2p.commerce.commerceapi.web.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AutenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public CommonResponse<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseHelper.ok(userService.register(registerRequest));
    }

    @DeleteMapping("/logout")
    public CommonResponse<String> logout(HttpServletRequest request) {
        return ResponseHelper.ok(userService.logout(request));
    }

}
