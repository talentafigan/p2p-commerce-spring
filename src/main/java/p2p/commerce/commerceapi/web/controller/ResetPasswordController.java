package p2p.commerce.commerceapi.web.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ChangePasswordRequest;
import p2p.commerce.commerceapi.web.dto.CheckOtpRequest;
import p2p.commerce.commerceapi.web.dto.CodeOtpRequest;
import p2p.commerce.commerceapi.web.model.CodeOtp;
import p2p.commerce.commerceapi.web.service.CodeOtpService;

@RestController
@RequestMapping("/api/reset-password")
public class ResetPasswordController {

    private CodeOtpService codeOtpService;

    @Autowired
    public ResetPasswordController(CodeOtpService codeOtpService) {
        this.codeOtpService = codeOtpService;
    }

    @SneakyThrows
    @PostMapping("/request")
    public CommonResponse<CodeOtp> resetPassword(@RequestBody CodeOtpRequest codeOtpRequest) {
        return ResponseHelper.ok(codeOtpService.createToken(codeOtpRequest));
    }

    @PostMapping("/check-code-active")
    public CommonResponse<String> checkActiveToken(@RequestBody CheckOtpRequest checkOtpRequest) {
        return ResponseHelper.ok(codeOtpService.checkActive(checkOtpRequest));
    }

    @PostMapping("/new-password")
    public CommonResponse<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseHelper.ok(codeOtpService.changePassword(changePasswordRequest));
    }
}
