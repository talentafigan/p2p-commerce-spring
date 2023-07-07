package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import p2p.commerce.commerceapi.configuration.XenditConfig;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private WalletTransactionRepository walletTransactionRepository;
    private AuthenticationFacade authenticationFacade;
    private ClientRepository clientRepository;
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


}
