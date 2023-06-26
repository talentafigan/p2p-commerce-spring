package p2p.commerce.commerceapi.web.service;

import freemarker.template.TemplateException;
import p2p.commerce.commerceapi.web.dto.ChangePasswordRequest;
import p2p.commerce.commerceapi.web.dto.CheckOtpRequest;
import p2p.commerce.commerceapi.web.dto.CodeOtpRequest;
import p2p.commerce.commerceapi.web.model.CodeOtp;

import javax.mail.MessagingException;
import java.io.IOException;

public interface CodeOtpService {
    CodeOtp createToken(CodeOtpRequest codeOtpRequest) throws TemplateException, MessagingException, IOException;
    String checkActive(CheckOtpRequest checkOtpRequest);

    String changePassword(ChangePasswordRequest changePasswordRequest);
}
