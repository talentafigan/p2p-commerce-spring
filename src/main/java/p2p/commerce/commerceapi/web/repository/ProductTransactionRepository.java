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
    @Query(value = "SELECT a.* FROM product_transactions a WHERE a.client_id = ?1 AND a.product_name LIKE CONCAT('%', ?2,'%') AND CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?3, '%') AND a.product_transaction_status_id = COALESCE(?4, a.product_transaction_status_id)", nativeQuery = true)
    List<ProductTransactions> findAllByClientAndProductTransactionStatus(int clients,String productName,String createDate, Integer productTransactionStatus);
    List<ProductTransactions> findAllByProductTransactionStatus(ProductTransactionStatus productTransactionStatus);
    List<ProductTransactions> findAllByProduct(Products products);

    @Query(value = "SELECT COUNT(a) FROM product_transactions a WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1, '%')", nativeQuery = true)
    long countProductTransactionThisDate(String date);
    Optional<ProductTransactions> findByProductAndProductTransactionStatus(Products products, ProductTransactionStatus productTransactionStatus);

}
