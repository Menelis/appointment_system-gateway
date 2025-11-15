package co.appointment.config;

import co.appointment.shared.model.CorsSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    CorsWebFilter corsWebFilter(final AppConfigProperties appConfigProperties) {
        CorsSettings corsSettings = appConfigProperties.getCors();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(corsSettings.getAllowedOrigins());
        corsConfig.setMaxAge(corsSettings.getMaxAge());
        corsConfig.setAllowedMethods(corsSettings.getAllowedMethods());
        corsConfig.setAllowedHeaders(corsSettings.getAllowedHeaders());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
