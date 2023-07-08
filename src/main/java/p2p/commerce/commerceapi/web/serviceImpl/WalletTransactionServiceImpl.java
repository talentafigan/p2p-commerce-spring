package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import p2p.commerce.commerceapi.configuration.XenditConfig;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.repository.TransactionTypeRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private WalletTransactionRepository walletTransactionRepository;
    private AuthenticationFacade authenticationFacade;
    private ClientRepository clientRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private StatusRepository statusRepository;
    private XenditConfig xenditConfig;

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public List<WalletTransaction> getAll(String date) {
        Users user= authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
        if (StringUtils.isEmpty(date)) {
            return walletTransactionRepository.findFirst5ByClientOrderByCreateDateDesc(clients).stream().map(e -> {
                e.setInvoice(xenditConfig.findById(e.getPaymentId()));
                return e;
            }).collect(Collectors.toList());
        }
        return walletTransactionRepository.findAllByClientQ(clients.getClientId(), date).stream().map(e -> {
            e.setInvoice(xenditConfig.findById(e.getPaymentId()));
            return e;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<WalletTransaction> findAll() {
        return walletTransactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public WalletTransaction findById(int id) {
        return walletTransactionRepository.findById(id).orElseThrow(() -> new BussinesException("Transaction ID NOT FOUND"));
    }

    @Transactional
    @Override
    public WalletTransaction createDebitConsultation(Users user, Clients clients, int amount) {
        WalletTransaction crtWltTrns = WalletTransaction.builder()
                .transactionType(transactionTypeRepository.findById(2).get())
                .status(statusRepository.findById(1).get())
                .user(user)
                .type("d")
                .client(clients)
                .amount(amount)
                .build();
        WalletTransaction res = walletTransactionRepository.save(crtWltTrns);
        res.setTransactionId(String.valueOf(res.getWalletTransactionId()));
        return walletTransactionRepository.save(res);
    }

    @Transactional
    @Override
    public List<WalletTransaction> findAllActive() {
        return walletTransactionRepository.findAllByStatus(statusRepository.findById(1).get());
    }


}
