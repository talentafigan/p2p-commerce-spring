package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;

import java.util.List;

public interface ProductTransactionStatusService {
    List<ProductTransactionStatus> findAll();
    ProductTransactionStatus findById(int id);
}
