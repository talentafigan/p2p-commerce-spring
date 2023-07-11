package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.AnalyticsResponse;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.AnalyticsService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private ConsultationRepository consultationRepository;
    private ProductTransactionRepository productTransactionRepository;
    private SellesRepository sellesRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public AnalyticsResponse findAnalyticsAdmin() {

        Date date = new Date();
        date.setMonth(new Date().getMonth()-1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String format = formatter.format(date);
        long listConsultation = consultationRepository.count();
        long newConsultation = consultationRepository.countSubConsultant(format);
        return AnalyticsResponse.builder()
                .consultation(listConsultation)
                .subConsultationPercent(((((listConsultation+ 0.0))-(newConsultation+ 0.0))/(listConsultation))*100.0)
                .transaction(productTransactionRepository.count())
                .subTransaction(productTransactionRepository.countProductTransactionThisDate(format))
                .totalStudent(clientRepository.count())
                .subTotalMentor(userRepository.countUserCurrentMonth(format, 2))
                .totalMentor(sellesRepository.count())
                .subTotalStudent(userRepository.countUserCurrentMonth(format, 3))
                .build();
    }

    @Override
    public AnalyticsResponse findAnalyticsSeller() {
        return null;
    }
}
