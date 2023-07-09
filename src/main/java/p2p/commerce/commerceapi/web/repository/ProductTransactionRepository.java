package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;
import p2p.commerce.commerceapi.web.model.ProductTransactions;

import java.util.List;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransactions, Integer> {
    List<ProductTransactions> findAllByProductTransactionStatusAndClient(ProductTransactionStatus productTransactionStatus, Clients clients);
}
