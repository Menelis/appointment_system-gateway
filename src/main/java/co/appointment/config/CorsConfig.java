package co.appointment.config;

import co.appointment.shared.model.CorsSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@Slf4j
public class CorsConfig {

    private final CorsSettings corsSettings;

    public CorsConfig(final AppConfigProperties appConfigProperties) {
        this.corsSettings = appConfigProperties.getCors();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        log.debug("Allowed Origins:{}", corsSettings.getAllowedOrigins());
        log.debug("Allowed Headers:{}", corsSettings.getAllowedHeaders());
        log.debug("Allowed Methods:{}", corsSettings.getAllowedMethods());
        config.setAllowedOrigins(corsSettings.getAllowedOrigins());
        config.setAllowedHeaders(corsSettings.getAllowedHeaders());
        config.setAllowedMethods(corsSettings.getAllowedMethods());
        config.setAllowCredentials(true);
        config.setMaxAge(corsSettings.getMaxAge());
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
