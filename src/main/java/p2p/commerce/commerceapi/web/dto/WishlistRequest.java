package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Products;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class WishlistRequest {
    private int clientId;
    private int productId;
}
