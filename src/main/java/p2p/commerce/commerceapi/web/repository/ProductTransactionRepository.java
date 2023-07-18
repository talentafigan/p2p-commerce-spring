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
    @Query(value = "SELECT a.* FROM product_transactions a " +
            "INNER JOIN products p on (a.product_id =p .product_id) " +
            "WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', ?2,'%')) AND " +
            "CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?3, '%') AND " +
            "p.seller_id = ?1 AND " +
            "a.product_transaction_status_id = COALESCE(?4, a.product_transaction_status_id)", nativeQuery = true)
    List<ProductTransactions> findAllBySeller(int sellerId, String productName,String createDate, Integer productTransactionStatus);
    @Query(value = "SELECT a.* FROM product_transactions a INNER JOIN products p on (a.product_id = p.product_id) WHERE a.client_id = ?1 AND LOWER(p.product_name) LIKE LOWER(CONCAT('%', ?2,'%')) AND CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?3, '%') AND a.product_transaction_status_id = COALESCE(?4, a.product_transaction_status_id)", nativeQuery = true)
    List<ProductTransactions> findAllByClient(int clients,String productName,String createDate, Integer productTransactionStatus);
    @Query(value = "SELECT a.* FROM product_transactions a INNER JOIN products p on (a.product_id = p.product_id) WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', ?1,'%')) AND CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?2, '%') AND a.product_transaction_status_id = COALESCE(?3, a.product_transaction_status_id)", nativeQuery = true)
    List<ProductTransactions> findAllByAdmin(String productName,String createDate, Integer productTransactionStatus);
    List<ProductTransactions> findAllByProduct(Products products);

    @Query(value = "SELECT COUNT(a) FROM product_transactions a WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1, '%')", nativeQuery = true)
    long countProductTransactionThisDate(String date);

    @Query(value = "SELECT COUNT(a) FROM product_transactions a " +
            "JOIN products p on a.product_id = p.product_id " +
            "JOIN sellers s on p.seller_id = s.seller_id " +
            "WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1, '%') " +
            "AND s.seller_id = ?2 " +
            "AND a.product_transaction_status_id = 5", nativeQuery = true)
    long countProductTransactionThisSeller(String date, int sellerId);


    @Query(value = "SELECT a.* FROM product_transactions a " +
            "JOIN products p on a.product_id = p.product_id " +
            "JOIN sellers s on p.seller_id = s.seller_id " +
            "WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1, '%') " +
            "AND s.seller_id = ?2 " +
            "AND a.product_transaction_status_id = 5", nativeQuery = true)
    List<ProductTransactions> amountProductTransactionThisSeller(String date, int sellerId);
}
