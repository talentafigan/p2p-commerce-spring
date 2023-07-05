package p2p.commerce.commerceapi.web.serviceImpl;

import com.xendit.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.XenditConfig;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.dto.TopUpResponse;
import p2p.commerce.commerceapi.web.dto.TopupRequest;
import p2p.commerce.commerceapi.web.dto.WalletResponse;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.repository.TransactionTypeRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;
import p2p.commerce.commerceapi.web.service.ParamService;
import p2p.commerce.commerceapi.web.service.WalletService;

import java.util.HashMap;
import java.util.Random;


@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private ClientRepository clientRepository;
    private AuthenticationFacade authenticationFacade;
    private WalletTransactionRepository walletTransactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private XenditConfig xenditConfig;
    private StatusRepository statusRepository;
    private ParamService paramService;

    @Transactional(readOnly = true)
    @Override
    public WalletResponse getWallet() {
        Users user= authenticationFacade.getAuthentication();
        Clients client = clientRepository.findByUser(user);
        return WalletResponse.builder().balance(client.getBalance()).build();
    }

    @Transactional
    @Override
    public TopUpResponse topUp(TopupRequest topupRequest) {
        Users user = authenticationFacade.getAuthentication();
        Clients clients = clientRepository.findByUser(user);
        WalletTransaction crtWltTrns = WalletTransaction.builder()
                .transactionId(createIdTransaction())
                .transactionType(transactionTypeRepository.findById(1).get())
                .totalPayment(topupRequest.getAmount() * paramService.priceCoin())
                .status(statusRepository.findById(2).get())
                .user(user)
                .client(clients)
                .fee(paramService.adminFee())
                .amount(topupRequest.getAmount())
                .build();
        WalletTransaction wltTrsRes =  walletTransactionRepository.save(crtWltTrns);
        Invoice invoice =  xenditConfig.createInvoice(wltTrsRes, clients);
        wltTrsRes.setPaymentId(invoice.getId());
        walletTransactionRepository.save(wltTrsRes);
        return TopUpResponse.builder()
                .walletTransaction(wltTrsRes)
                .invoice(invoice)
                .client(clients)
                .build();
    }


    private String createIdTransaction() {
        while (true) {
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random rnd = new Random();
            int len = 15;
            StringBuilder sb = new StringBuilder(len);
            sb.append("GP-");
            for (int i = 0; i < len; i++)
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            String id = sb.toString();
            if (walletTransactionRepository.findByTransactionId(id).isPresent()) continue;
            return id;
        }
    }
}
