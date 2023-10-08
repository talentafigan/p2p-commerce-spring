package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p2p.commerce.commerceapi.configuration.auditing.DateAll;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prod")
public class Prod  extends DateAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Lob
    @Column(name = "product_description")
    private String productDescription;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "rating")
    private float rating;
}
