package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Status;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserAuthenticationLogRepository extends JpaRepository<UserAuthenticationLog, Integer> {
    Optional<UserAuthenticationLog> findByUserAndCreateDateBefore(Users users, Date createDate);
    Optional<UserAuthenticationLog> findByUserAndStatus(Users users, Status status);
    Optional<UserAuthenticationLog> findByAccessTokenAndCreateDateBefore(String accessToken, Date createDate);
    Optional<UserAuthenticationLog> findByAccessTokenAndStatus(String accessToken, Status status);
}
