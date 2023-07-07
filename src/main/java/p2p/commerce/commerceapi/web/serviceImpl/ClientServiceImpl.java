package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.service.ClientService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Clients> findAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Clients findById(int id) {
        return clientRepository.findById(id).orElseThrow(() -> new BussinesException("CLIENT ID NOT FOUND"));
    }
}
