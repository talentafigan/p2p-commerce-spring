package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "SELECT COUNT(a) FROM consultations a WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1 , '%')", nativeQuery = true)
    long countSubConsultant(String date);
    Optional<Consultations> findByStatusAndClient(Status status, Clients clients);
}
