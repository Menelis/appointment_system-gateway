package co.appointment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(final Authentication authentication) {
        String token = authentication.getCredentials().toString();
        if(!jwtService.validateToken(token)) {
            return Mono.error(new BadCredentialsException("Invalid JWT token"));
        }
        String username = jwtService.extractEmail(token);
        return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authentication.getAuthorities()));
    }
}
