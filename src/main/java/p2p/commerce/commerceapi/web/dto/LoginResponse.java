package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;
import p2p.commerce.commerceapi.web.model.Status;

@Getter
@Setter
public class LoginResponse {
    private int userAuthenticationLogId;
    private String accessToken;
    private Status status;
}
