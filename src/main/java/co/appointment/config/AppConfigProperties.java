package co.appointment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {
    private OpenApiSettings openApi;
    private CorsSettings cors;
    private String[] whiteList;
}
