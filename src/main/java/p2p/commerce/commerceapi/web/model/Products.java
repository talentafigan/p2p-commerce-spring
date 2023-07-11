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
@Table(name = "products")
public class Products extends DateAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @OneToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategories productCategorie;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Sellers seller;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
