package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_transaction_status")
public class ProductTransactionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_transaction_status_id")
    private int productTransactionStatusId;

    @Column(name = "product_transaction_status_name", nullable = false)
    private String productTransactionStatusName;
}
