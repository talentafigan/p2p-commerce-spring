package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Admins;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer> {
    Optional<Clients> findByUsername(String username);
    Boolean existsByUsername(String username);
    Clients findByUser(Users users);
}
