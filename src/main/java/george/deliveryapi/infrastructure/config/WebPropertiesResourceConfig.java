package george.deliveryapi.infrastructure.config;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebPropertiesResourceConfig {

    @Bean
    public Resources resources() {
        return new Resources();
    }
}
