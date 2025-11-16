package co.appointment.config;

import co.appointment.shared.model.CorsSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http,
                                                            final AppConfigProperties appConfigProperties) throws Exception {
        CorsSettings corsSettings = appConfigProperties.getCors();
        return http
                .cors(cors -> cors.configurationSource(_ -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(corsSettings.getAllowedOrigins());
                    configuration.setAllowedMethods(corsSettings.getAllowedMethods());
                    configuration.setAllowedHeaders(corsSettings.getAllowedHeaders());
                    return configuration;
                }))
                .authorizeExchange(auth -> auth.anyExchange().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }
}
