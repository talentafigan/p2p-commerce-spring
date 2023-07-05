package p2p.commerce.commerceapi.web.dto;

import com.xendit.model.Invoice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

@Builder
@Getter
@Setter
public class TopUpResponse {
    private Invoice invoice;
    private WalletTransaction walletTransaction;
    private Clients client;
}
