package co.appointment;

import co.appointment.config.AppConfigProperties;
import co.appointment.shared.service.EncryptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EncryptionService.class) })
@EnableConfigurationProperties({ AppConfigProperties.class })
public class AppointmentGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentGatewayApplication.class, args);
	}

}
