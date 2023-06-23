package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByStatusId(int statusId);
}
