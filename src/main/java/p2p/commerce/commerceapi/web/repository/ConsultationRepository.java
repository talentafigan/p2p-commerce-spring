package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Consultations;
import p2p.commerce.commerceapi.web.model.Status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultations, Integer> {
    Boolean existsByConversationIdAndStatus(String conversationId, Status status);


    List<Consultations> findAllByCreateDateBefore(Date date);
    long countByStatus(Status status);
    Optional<Consultations> findByStatusAndClient(Status status, Clients clients);
}
