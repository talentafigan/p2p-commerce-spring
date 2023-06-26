package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeOtpRequest {
    private String email;
    private int userType;
}
