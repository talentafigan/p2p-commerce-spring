package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private int productPrice;
    private String productName;
    private Integer productCategoryId;
    private String productDescription;
}
