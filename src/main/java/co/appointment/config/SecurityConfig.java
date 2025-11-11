package co.appointment.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    //TODO: Handle multiple identity providers
//    @Value("${spring.security.oauth2.client.provider.spring.issuer-uri}")
//    private String issuerUri;
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(auth -> auth.anyExchange().authenticated())
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .build();
//    }
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
//    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http,
//                                                         final AuthenticationWebFilter jwtAuthenticationWebFilter,
//                                                         final AppConfigProperties appConfigProperties,
//                                                         final ObjectMapper objectMapper) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .exceptionHandling(exception -> exception.accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper))
//                        .authenticationEntryPoint(new CustomAuthEntryPoint(objectMapper)))
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers(appConfigProperties.getWhiteList()).permitAll()
//                        .anyExchange().authenticated()
//                )
//                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
//                .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();
//    }
//    @Bean
//    public AuthenticationWebFilter jwtAuthenticationWebFilter(final JwtReactiveAuthenticationManager authManager,
//                                                              final JwtServerAuthenticationConverter converter) {
//        AuthenticationWebFilter filter = new AuthenticationWebFilter(authManager);
//        filter.setServerAuthenticationConverter(converter);
//        return filter;
//    }
}
