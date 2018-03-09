package thomastodon.io.parameterizedintegrationtests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ColorConfiguration {

    @Bean
    ColorService colorService() {
        return new ColorService();
    }
}
