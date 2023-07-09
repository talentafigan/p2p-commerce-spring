package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Products;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransactions, Integer> {
    List<ProductTransactions> findAllByProductTransactionStatusAndClient(ProductTransactionStatus productTransactionStatus, Clients clients);
    Optional<ProductTransactions> findByProduct(Products products);
}
