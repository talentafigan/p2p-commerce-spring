package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p2p.commerce.commerceapi.configuration.auditing.DateCreate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_type")
public class TransactionType extends DateCreate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionTypeId;

    @Column(name = "name")
    private String name;
}
