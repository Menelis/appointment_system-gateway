package co.appointment.config;

import co.appointment.service.JwtReactiveAuthenticationManager;
import co.appointment.service.JwtServerAuthenticationConverter;
import co.appointment.shared.constant.RoleConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http,
                                                         final AuthenticationWebFilter jwtAuthenticationWebFilter,
                                                         final AppConfigProperties appConfigProperties) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(appConfigProperties.getWhiteList()).permitAll()
                        .pathMatchers(appConfigProperties.getAdminRoutes()).hasAnyRole(RoleConstants.ADMIN_ROLE)
                        .pathMatchers(appConfigProperties.getUserRoutes()).hasAnyRole(RoleConstants.USER_ROLE)
                        .anyExchange().authenticated()
                )
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                //.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                //.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
    @Bean
    public AuthenticationWebFilter jwtAuthenticationWebFilter(final JwtReactiveAuthenticationManager authManager,
                                                              final JwtServerAuthenticationConverter converter) {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(authManager);
        filter.setServerAuthenticationConverter(converter);
        return filter;
    }
}
