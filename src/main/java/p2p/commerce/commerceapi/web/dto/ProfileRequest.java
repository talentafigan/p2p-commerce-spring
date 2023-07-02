package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String fullname;
    private String username;
    private String email;
    private String phone;
}
