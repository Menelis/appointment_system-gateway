package co.appointment.service;

import co.appointment.handler.AuthFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
public class CustomAuthEntryPoint implements ServerAuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException exception) {

        return AuthFailureHandler.buildAuthFailureMessage(
                exchange.getResponse(),
                objectMapper,
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                exception.getMessage(),
                exchange.getRequest().getPath().toString());
    }
}
