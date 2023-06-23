package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Admins;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Optional;

@Repository
public interface SellesRepository extends JpaRepository<Sellers, Integer> {
    Optional<Sellers> findByUsername(String username);
    Boolean existsByUsername(String username);
    Sellers findByUser(Users users);
}
