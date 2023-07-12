package p2p.commerce.commerceapi.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_categories")
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private int productCategoryId;
    @Column(name = "product_category_name", nullable = false)
    private String productCategoryName;
    @Column(name = "product_category_parent_id")
    private Integer productCategoryParentId;
}
