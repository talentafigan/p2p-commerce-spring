package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_assets")
public class ProductAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_asset_id")
    private int productAssetId;

    @Column(name = "product_asset_type")
    private String productAssetType;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
