package p2p.commerce.commerceapi.configuration.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.configuration.init_token.TokenProvider;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.repository.UserAuthenticationLogRepository;

import java.util.List;

@Component
public class InspectionToken {
    private UserAuthenticationLogRepository userAuthenticationLogRepository;
    private StatusRepository statusRepository;
    private TokenProvider tokenProvider;

    @Autowired
    public InspectionToken(UserAuthenticationLogRepository userAuthenticationLogRepository, StatusRepository statusRepository, TokenProvider tokenProvider) {
        this.userAuthenticationLogRepository = userAuthenticationLogRepository;
        this.statusRepository = statusRepository;
        this.tokenProvider = tokenProvider;
    }

    @Async
    public void inspectionActiveToken() {
        List<UserAuthenticationLog> tokens = userAuthenticationLogRepository.findAll();
        if (tokens==null) return;
        for (UserAuthenticationLog token : tokens) {
            if (!tokenProvider.validateToken(token.getAccessToken())) {
                token.setStatus(statusRepository.findById(3).get());
            }
        }
        System.out.println("Proccess ALL DONE");
        userAuthenticationLogRepository.saveAll(tokens);
    }
}
