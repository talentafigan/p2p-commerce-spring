package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.AnalyticsResponse;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.AnalyticsService;

@Slf4j
@Service
@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private ConsultationRepository consultationRepository;
    private ProductTransactionRepository productTransactionRepository;
    private SellesRepository sellesRepository;
    private ClientRepository clientRepository;
    private StatusRepository statusRepository;

    @Transactional(readOnly = true)
    @Override
    public AnalyticsResponse findAnalyticsAdmin() {
        return AnalyticsResponse.builder()
                .consultation(consultationRepository.countByStatus(statusRepository.findById(1).get()))
                .transaction(productTransactionRepository.count())
                .totalStudent(clientRepository.count())
                .totalMentor(sellesRepository.count())
                .build();
    }

    @Override
    public AnalyticsResponse findAnalyticsSeller() {
        return null;
    }
}
