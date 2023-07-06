package p2p.commerce.commerceapi.configuration.web;

import com.xendit.model.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.CallBackDtoRequest;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.repository.WalletTransactionRepository;

@RestController
@RequestMapping("/api/payment/callback")
@AllArgsConstructor
public class CallbackSuccessController {

    private WalletTransactionRepository walletTransactionRepository;
    private ClientRepository clientRepository;
    private StatusRepository statusRepository;
    
    @Async
    public void updateBalance(String externalId) {
        WalletTransaction wlt = walletTransactionRepository.findByTransactionId(externalId).orElseThrow(() -> new BussinesException("External ID NOT FOUND"));
        wlt.setStatus(statusRepository.findById(1).get());
        walletTransactionRepository.save(wlt);
        Clients client = wlt.getClient();
        client.setBalance(wlt.getAmount());
        clientRepository.save(client);
    }

    @PostMapping
    public CommonResponse<String> callbackPost(@RequestBody CallBackDtoRequest callBackDtoRequest) {
        updateBalance(callBackDtoRequest.getExternal_id());
        return ResponseHelper.ok("SUCCESS");
    }
}
