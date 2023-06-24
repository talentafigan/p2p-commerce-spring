package p2p.commerce.commerceapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import p2p.commerce.commerceapi.web.model.UserType;

import javax.persistence.Column;

@Getter
@Setter
public class RegisterResponse {
    private String fullname;
    private String username;
    private String email;
    private String phone;
    private UserType userType;
}
