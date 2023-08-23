package integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class AppIntegration {
    public static void main(String[] args) {
        SpringApplication.run(AppIntegration.class, args);
    }
}
