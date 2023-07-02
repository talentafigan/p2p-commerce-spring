package p2p.commerce.commerceapi.configuration.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.UserRepository;

@Component
public class AuthenticationFacade {
    @Autowired
    private UserRepository userRepository;

    public Users getAuthentication() {
        return userRepository.findByUsernameOrEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    public Authentication getAuthenticationGlobal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
