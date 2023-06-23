package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Admins;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Integer> {
    Optional<Admins> findByUsername(String username);
    Boolean existsByUsername(String username);
    Admins findByUser(Users users);
}
