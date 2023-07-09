package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.ProductTransactionRepository;
import p2p.commerce.commerceapi.web.repository.ProductTransactionStatusRepository;
import p2p.commerce.commerceapi.web.service.ProductTransactionService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductTransactionServiceImpl implements ProductTransactionService {

    private ProductTransactionRepository productTransactionRepository;
    private ProductTransactionStatusRepository productTransactionStatusRepository;
    private AuthenticationFacade authenticationFacade;
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactions> findAll() {
        return productTransactionRepository.findAll();
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
