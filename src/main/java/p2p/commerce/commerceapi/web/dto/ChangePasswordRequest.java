package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String code;
    private String newPassword;
}
