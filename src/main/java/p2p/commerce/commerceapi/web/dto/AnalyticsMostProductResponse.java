package p2p.commerce.commerceapi.web.dto;
import lombok.Getter;
import lombok.Setter;
import p2p.commerce.commerceapi.web.model.ProductCategories;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.Status;

@Getter
@Setter
public class AnalyticsMostProductResponse {
    private int productId;
    private int productPrice;
    private String productName;
    private String productDescription;
    private String image;
    private ProductCategories productCategories;
    private int rating;
    private Sellers seller;
    private Status status;
    private int totalTransaction;
}
