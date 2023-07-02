package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.UserType;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserType(UserType userType);
    @Query(value = "SELECT a.* FROM users a WHERE a.username = ?1 OR a.email = ?1", nativeQuery = true)

    Optional<Users> findByUsernameOrEmail(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
