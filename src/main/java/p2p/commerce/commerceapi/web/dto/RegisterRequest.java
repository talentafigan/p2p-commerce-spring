package p2p.commerce.commerceapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RegisterRequest {
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int userTypeId;
}
