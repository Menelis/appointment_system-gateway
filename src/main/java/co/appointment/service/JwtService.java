package co.appointment.service;

import co.appointment.config.AppConfigProperties;
import co.appointment.shared.constant.TokenConstants;
import co.appointment.shared.model.JwtSettings;
import co.appointment.shared.util.SharedJwtUtils;
import org.springframework.stereotype.Service;

/**
 * Service responsible for extracting claims from token and validate it.
 */
@Service
public class JwtService {

    private final JwtSettings jwtSettings;

    public JwtService(final AppConfigProperties appConfigProperties) {
        this.jwtSettings = appConfigProperties.getJwt();
    }
    public String extractEmail(String token) {
        return SharedJwtUtils.extractClaimByName(token, TokenConstants.EMAIL, jwtSettings.getSecret(), String.class);
    }
    public boolean validateToken(final String token) {
        return SharedJwtUtils.validateToken(token, jwtSettings.getSecret());
    }
}
