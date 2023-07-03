package p2p.commerce.commerceapi.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WalletResponse {
    private int balance;
}
