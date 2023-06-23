package p2p.commerce.commerceapi.web.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String key;
    private String password;
    private int userTypeId;
}
