package corso.java.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "corso.java.businessmodel.repositories" })
@EntityScan(basePackages = { "corso.java.businessmodel" })
public class WebAppContentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppContentManagementApplication.class, args);
	}

}
