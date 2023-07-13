package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.async.MainAsync;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.dto.ProductTransactionProofRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionStatusRequest;
import p2p.commerce.commerceapi.web.dto.RatingRequest;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.ProductTransactionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductTransactionServiceImpl implements ProductTransactionService {

    private ProductTransactionRepository productTransactionRepository;
    private ProductTransactionStatusRepository productTransactionStatusRepository;
    private AuthenticationFacade authenticationFacade;
    private ProductRepository productRepository;
    private ClientRepository clientRepository;
    private MainAsync mainAsync;
    private SellesRepository sellesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactions> findAll(Integer productTransactionStatusId) {
        Users user = authenticationFacade.getAuthentication();
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            return productTransactionRepository.findAllByProductTransactionStatus(productTransactionStatusRepository.findById(productTransactionStatusId).orElse(null));
        }
        if (user.getUserType().getUserTypeName().equals("Seller")) {
            List<ProductTransactions> productTransactions = new ArrayList<>();
            Sellers seller = sellesRepository.findByUser(user);
            List<Products> listProduct = productRepository.findAllBySeller(seller);
            for (Products p : listProduct) {
                var res = productTransactionRepository.findByProductAndProductTransactionStatus(p, productTransactionStatusRepository.findById(productTransactionStatusId).orElse(null));
                if (res.isPresent()) {
                    productTransactions.add(res.get());
                }
            }
            return productTransactions;
        } else {
            Clients clients = clientRepository.findByUser(user);
            return  productTransactionRepository.findAllByClientAndProductTransactionStatus(clients, productTransactionStatusRepository.findById(productTransactionStatusId).orElse(null));
        }

    }

    @Override
    public ProductTransactions rating(int productTransactionId, RatingRequest ratingRequest) {
        if (ratingRequest.getRating() < 1 || ratingRequest.getRating() > 5) throw new BussinesException("Rating can only contain 1-5");
        ProductTransactions productTransactions = productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
        if (productTransactions.getProductTransactionStatus().getProductTransactionStatusId() != 5) throw new BussinesException("Transaction status must be success to provide a rating");
        productTransactions.setRating(ratingRequest.getRating());
        productTransactions.setRatingDesctiption(ratingRequest.getRatingDescription());
        productTransactions = productTransactionRepository.save(productTransactions);
        mainAsync.updateRatingProduct();
        return productTransactions;
    }



    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactions> findAllActive() {
        Users user = authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
//        return productTransactionRepository.findAllByProductTransactionStatusAndClient(productTransactionStatusRepository.findById(5).get(), clients);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductTransactions findById(int productTransactionId) {
        return productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
    }

    @Transactional
    @Override
    public ProductTransactions createTransaction(ProductTransactionRequest productTransactionRequest) {
        Users user = authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
        Products products = productRepository.findById(productTransactionRequest.getProductId()).orElseThrow(() -> new BussinesException("Product ID NOT FOUND"));
        return productTransactionRepository.save(ProductTransactions.builder()
                .product(products)
                .amount(products.getProductPrice())
                .productTransactionStatus(productTransactionStatusRepository.findById(1).get())
                .client(clients)
                .build());
    }

    @Transactional
    @Override
    public ProductTransactions deleteTransaction(int productTransactionId) {
        Users user = authenticationFacade.getAuthentication();
        ProductTransactions productTransactions = productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
        if (productTransactions.getProductTransactionStatus().getProductTransactionStatusId() != 1 || productTransactions.getProductTransactionStatus().getProductTransactionStatusId() != 2) throw new BussinesException("Can't Cancel Transaction");
        productTransactions.setCanceledBy(user);
        productTransactions.setProductTransactionStatus(productTransactionStatusRepository.findById(6).get());
        productTransactions.setCancelDate(new Date());
        return productTransactionRepository.save(productTransactions);
    }

    @Transactional
    @Override
    public ProductTransactions proofTransaction(int productTransactionId, ProductTransactionProofRequest productTransactionProofRequest) {
        ProductTransactions productTransactions = productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
        productTransactions.setProof(productTransactions.getProof());
        productTransactions.setProductTransactionStatus(productTransactionStatusRepository.findById(3).get());
        return productTransactionRepository.save(productTransactions);
    }

    @Transactional
    @Override
    public ProductTransactions setStatusTransaction(int productTransactionId, ProductTransactionStatusRequest productTransactionStatusRequest) {
        ProductTransactions productTransactions = productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
        productTransactions.setProductTransactionStatus(productTransactionStatusRepository.findById(productTransactionStatusRequest.getProductStatusId()).orElseThrow(() -> new BussinesException("Product Transaction Status ID NOT FOUND")));
        return productTransactionRepository.save(productTransactions);
    }
}
