package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.CodeOtp;
import p2p.commerce.commerceapi.web.model.Users;

import java.util.Optional;

@Repository
public interface CodeOtpRespository extends JpaRepository<CodeOtp, Integer> {
    Optional<CodeOtp> findByCodeAndUsedIsFalse(String code);
    Optional<CodeOtp> findByUserAndUsedIsFalse(Users users);
    Optional<CodeOtp> findByCode(String code);
}
