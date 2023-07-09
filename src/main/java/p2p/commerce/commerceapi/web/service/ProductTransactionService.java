package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.ProductTransactions;

import java.util.List;

public interface ProductTransactionService {
    List<ProductTransactions> findAll();
    List<ProductTransactions> findAllActive();
    ProductTransactions findById(int productTransactionId);
}
