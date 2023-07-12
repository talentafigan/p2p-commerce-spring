package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Products;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransactions, Integer> {
    List<ProductTransactions> findAllByClientAndProductTransactionStatus(Clients clients, ProductTransactionStatus productTransactionStatus);
    List<ProductTransactions> findAllByProductTransactionStatus(ProductTransactionStatus productTransactionStatus);


    @Query(value = "SELECT COUNT(a) FROM product_transactions a WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1, '%')", nativeQuery = true)
    long countProductTransactionThisDate(String date);
    Optional<ProductTransactions> findByProductAndProductTransactionStatus(Products products, ProductTransactionStatus productTransactionStatus);

}
