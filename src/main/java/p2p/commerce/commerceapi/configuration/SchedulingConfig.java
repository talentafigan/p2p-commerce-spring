package p2p.commerce.commerceapi.configuration;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.web.model.Consultations;
import p2p.commerce.commerceapi.web.repository.ConsultationRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class SchedulingConfig {

    private ConsultationRepository consultationRepository;
    private StatusRepository statusRepository;

    @Async
    public void checkingExpireConsultation() {
        System.out.println("Scaduler running at " + new Date());
        List<Consultations> consultations = consultationRepository.findAllByCreateDateBefore(new Date());
        for (Consultations c : consultations) {
            c.setStatus(statusRepository.findById(3).get());
        }
    }

    @Scheduled(cron = "0 0 6,23 * * *")
//    @Scheduled(cron = "*/10 * * * * *")
    public void schedulingConfig() {
        checkingExpireConsultation();
    }
}

