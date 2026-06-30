package com.hms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = "com.hms"
)
@EnableJpaRepositories(basePackages = {
		"com.hms.hospital.repositories",
		"com.hms.doctor.repositories",
		"com.hms.user.repositories"
})
@EntityScan(
		basePackages = {
				"com.hms.common.model",
		}
)
@ConfigurationPropertiesScan
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
