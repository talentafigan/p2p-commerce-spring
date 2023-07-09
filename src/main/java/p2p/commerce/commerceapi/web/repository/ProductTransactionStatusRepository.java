package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;

@Repository
public interface ProductTransactionStatusRepository extends JpaRepository<ProductTransactionStatus, Integer> {
}
