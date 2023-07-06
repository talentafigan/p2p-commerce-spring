package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer> {

    @Query(value = "SELECT a.* FROM wallet_transactions a WHERE a.client_id = ?1 AND CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?2 , '%') ", nativeQuery = true)
    List<WalletTransaction> findAllByClientQ(int clientId, String date);

    List<WalletTransaction> findAllByClient(Clients clients);

    Optional<WalletTransaction> findByTransactionId(String transactionId);
}
