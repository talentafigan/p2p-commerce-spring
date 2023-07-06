package p2p.commerce.commerceapi.web.model;

import com.xendit.model.Invoice;
import lombok.*;
import p2p.commerce.commerceapi.configuration.auditing.DateAll;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet_transactions")
public class WalletTransaction extends DateAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_transaction_id")
    private int walletTransactionId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "total_payment")
    private float totalPayment;

    @Column(name = "amount")
    private int amount;

    @Column(name="type", columnDefinition = "VARCHAR(1)")
    private String type;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "fee")
    private float fee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @Transient
    private Object invoice;
}
