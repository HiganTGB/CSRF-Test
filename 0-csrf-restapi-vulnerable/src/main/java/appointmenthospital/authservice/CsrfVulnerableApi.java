package appointmenthospital.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class CsrfVulnerableApi {

    public static void main(String[] args) {
        SpringApplication.run(CsrfVulnerableApi.class, args);
    }

}
