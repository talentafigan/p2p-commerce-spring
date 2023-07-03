package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.dto.WalletResponse;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.service.WalletService;


@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private ClientRepository clientRepository;
    private AuthenticationFacade authenticationFacade;


    @Transactional(readOnly = true)
    @Override
    public WalletResponse getWallet() {
        Users user= authenticationFacade.getAuthentication();
        Clients client = clientRepository.findByUser(user);
        return WalletResponse.builder().balance(client.getBalance()).build();
    }
}
