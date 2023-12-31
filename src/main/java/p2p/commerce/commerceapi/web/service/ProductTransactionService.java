package p2p.commerce.commerceapi.web.service;

import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.*;
import p2p.commerce.commerceapi.web.model.ProductTransactions;

import java.util.List;

public interface ProductTransactionService {
    List<ProductTransactions> findAll(String productName, String createDate, Integer productTransactionStatusId);

    ProductTransactions rating(int productTransactionId, RatingRequest ratingRequest);

    List<ProductTransactions> findAllActive();
    ProductTransactions findById(int productTransactionId);

    ProductTransactions createTransaction(ProductTransactionRequest productTransactionRequest);


    ProductTransactions approveTransactionSeller(int productTransactionId, ApproveRequest approveRequest);

    ProductTransactions deleteTransaction(int productTransactionId);


    ProductTransactions proofTransaction(int productTransactionId, ProductTransactionProofRequest productTransactionProofRequest);

    ProductTransactions setStatusTransaction(int productTransactionId, ProductTransactionStatusRequest productTransactionStatusRequest);
}
