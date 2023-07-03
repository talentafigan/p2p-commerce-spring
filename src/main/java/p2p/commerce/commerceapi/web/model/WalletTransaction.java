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

    @Column(name = "title")
    private String title;

    @Column(name = "balance")
    private int balance;

    @Column(name = "is_topup")
    private Boolean isTopup = true;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Clients client;
}
