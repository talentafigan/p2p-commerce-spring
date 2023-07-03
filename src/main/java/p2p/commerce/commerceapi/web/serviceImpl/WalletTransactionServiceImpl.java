package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

@Slf4j
@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private WalletTransactionRepository walletTransactionRepository;
    private AuthenticationFacade authenticationFacade;
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<WalletTransaction> getAll(int page, int size) {
        Users user= authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
        Pageable pageable = PageRequest.of(page, size);
        return walletTransactionRepository.findAllByClient(clients, pageable);
    }
}
