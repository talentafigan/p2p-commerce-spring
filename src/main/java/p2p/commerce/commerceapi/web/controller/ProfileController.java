package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ProfileChangePasswordRequest;
import p2p.commerce.commerceapi.web.dto.ProfileRequest;
import p2p.commerce.commerceapi.web.dto.ProfileResponse;
import p2p.commerce.commerceapi.web.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public CommonResponse<ProfileResponse> profileMe() {
        return ResponseHelper.ok(userService.profileMe());
    }

    @PutMapping
    public CommonResponse<ProfileResponse> profileEdit(@RequestBody ProfileRequest profileRequest) {
        return ResponseHelper.ok(userService.editProfile(profileRequest));
    }

    @PutMapping("/new-password")
    public CommonResponse<String> profileEditPassword(@RequestBody ProfileChangePasswordRequest passwordRequest) {
        return ResponseHelper.ok(userService.changePasswordProfile(passwordRequest));
    }
}
