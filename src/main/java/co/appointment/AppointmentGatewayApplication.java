package co.appointment;

import co.appointment.config.AppConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SuppressWarnings({"HideUtilityClassConstructor"})
@SpringBootApplication
@EnableConfigurationProperties({AppConfigProperties.class})
public class AppointmentGatewayApplication {

    static void main(final String[] args) {
        SpringApplication.run(AppointmentGatewayApplication.class, args);
    }
}
