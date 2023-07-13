package p2p.commerce.commerceapi.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import p2p.commerce.commerceapi.configuration.auditing.DateCreate;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_transactions")
public class ProductTransactions extends DateCreate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_transactions_id")
    private int productTransactionId;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "proof")
    private String proof;

    @Column(name = "canceled_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "product_transaction_status_id")
    private ProductTransactionStatus productTransactionStatus;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "canceled_by")
    private Users canceledBy;

    @Column(name = "rating")
    private int rating = 0;

    @Column(name = "rating_desctiption")
    private String ratingDesctiption;
}
