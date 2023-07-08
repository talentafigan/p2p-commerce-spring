package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Consultations;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.ConsultationService;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private AuthenticationFacade authenticationFacade;
    private StatusRepository statusRepository;
    private AdminRepository adminRepository;
    private ClientRepository clientRepository;
    private ConsultationRepository consultationRepository;
    private WalletTransactionService walletTransactionService;

    private static int consultingFee = 1;

    @Transactional
    @Override
    public Consultations postConsultation() {
        Users user = authenticationFacade.getAuthentication();
        Clients client= clientRepository.findByUser(user);
        if (client.getBalance() <= 0) throw new BussinesException("Your coins are not enough");
        client.setBalance(client.getBalance() - consultingFee);
        walletTransactionService.createDebitConsultation(user, client, consultingFee);
        clientRepository.save(client);
        Consultations consultation=
                Consultations.builder()
                .admin(adminRepository.findById(3).orElseThrow(() -> new BussinesException("Admin ID NOT FOUND")))
                .client(client)
                .status(statusRepository.findById(1).get())
                        .conversationId(UUID.randomUUID().toString())
                .build();
        return consultationRepository.save(consultation);
    }

    @Transactional(readOnly = true)
    @Override
    public Consultations findById(int id) {
        return consultationRepository.findById(id).orElseThrow(() -> new BussinesException("Consultation ID NOT FOUND"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consultations> findAllConsultation() {
        return consultationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consultations> findAllConsultationActive() {
        return consultationRepository.findAllByStatus(statusRepository.findById(1).get());
    }
}
