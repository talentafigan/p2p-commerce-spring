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

    @Query(value = "SELECT COUNT(a) FROM users a WHERE CAST(a.created_date AS VARCHAR) LIKE CONCAT('', ?1 , '%') AND a.user_type_id = ?2", nativeQuery = true)
    long countUserCurrentMonth(String date, int userTypeId);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
