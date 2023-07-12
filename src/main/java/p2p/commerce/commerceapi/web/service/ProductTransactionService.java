package p2p.commerce.commerceapi.web.service;

import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.ProductTransactionProofRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionStatusRequest;
import p2p.commerce.commerceapi.web.model.ProductTransactions;

import java.util.List;

public interface ProductTransactionService {
    List<ProductTransactions> findAll(Integer productTransactionStatusId);
    List<ProductTransactions> findAllActive();
    ProductTransactions findById(int productTransactionId);

    ProductTransactions createTransaction(ProductTransactionRequest productTransactionRequest);


    ProductTransactions deleteTransaction(int productTransactionId);


    ProductTransactions proofTransaction(int productTransactionId, ProductTransactionProofRequest productTransactionProofRequest);

    ProductTransactions setStatusTransaction(int productTransactionId, ProductTransactionStatusRequest productTransactionStatusRequest);
}
