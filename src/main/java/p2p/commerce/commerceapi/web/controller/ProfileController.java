package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ProfileResponse;
import p2p.commerce.commerceapi.web.service.UserService;

@RestController
@RequestMapping("/api")
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/me")
    public CommonResponse<ProfileResponse> profileMe() {
        return ResponseHelper.ok(userService.profileMe());
    }
}
