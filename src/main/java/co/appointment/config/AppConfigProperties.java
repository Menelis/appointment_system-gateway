package co.appointment.config;

import co.appointment.shared.model.CorsSettings;
import co.appointment.shared.model.OpenApiSettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {

    private OpenApiSettings openApi;
    private CorsSettings cors;
}
