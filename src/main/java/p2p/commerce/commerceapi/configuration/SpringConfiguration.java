package p2p.commerce.commerceapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import p2p.commerce.commerceapi.configuration.data.ApplicationContextProvider;

@Configuration
public class SpringConfiguration {
    @Bean
    public static ApplicationContextProvider contextProvider() {
        return new ApplicationContextProvider();
    }

}
