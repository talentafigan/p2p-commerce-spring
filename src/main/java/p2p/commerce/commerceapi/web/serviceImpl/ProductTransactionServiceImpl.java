package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.ProductTransactionService;

import java.util.ArrayList;
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
    private SellesRepository sellesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactions> findAll() {
        Users user = authenticationFacade.getAuthentication();
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            return productTransactionRepository.findAll();
        }

        List<ProductTransactions> productTransactions = new ArrayList<>();
        Sellers seller = sellesRepository.findByUser(user);
        List<Products> listProduct = productRepository.findAllBySeller(seller);
        for (Products p:listProduct) {
            var res = productTransactionRepository.findByProduct(p);
            if (res.isPresent()) {
                productTransactions.add(res.get());
            }
        }
        return productTransactions;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactions> findAllActive() {
        Users user = authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
        return productTransactionRepository.findAllByProductTransactionStatusAndClient(productTransactionStatusRepository.findById(5).get(), clients);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductTransactions findById(int productTransactionId) {
        return productTransactionRepository.findById(productTransactionId).orElseThrow(() -> new BussinesException("Product Transaction ID NOT FOUND"));
    }
}
