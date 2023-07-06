package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.XenditConfig;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private WalletTransactionRepository walletTransactionRepository;
    private AuthenticationFacade authenticationFacade;
    private ClientRepository clientRepository;
    private XenditConfig xenditConfig;

    @Transactional(readOnly = true)
    @Override
    public List<WalletTransaction> getAll(String date) {
        Users user= authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
//        if (date.equals("")) {
            return walletTransactionRepository.findAllByClient(clients).stream().map(e -> {
                e.setInvoice(xenditConfig.findById(e.getPaymentId()));
                return e;
            }).collect(Collectors.toList());
//        } else {
//        return walletTransactionRepository.findAllByClientQ(clients.getClientId(), new Date(date)).stream().map(e -> {
//            e.setInvoice(xenditConfig.findById(e.getPaymentId()));
//            return e;
//        }).collect(Collectors.toList());
//        }
    }
}
