package p2p.commerce.commerceapi.web.model;

import lombok.*;
import p2p.commerce.commerceapi.configuration.auditing.DateCreate;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlist")
public class Wishlist extends DateCreate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishListId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients clientId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products productId;

}
