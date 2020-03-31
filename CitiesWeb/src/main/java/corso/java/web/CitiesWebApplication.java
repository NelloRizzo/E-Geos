package corso.java.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Gestore della web application.
 * 
 * @author nello
 *
 */
@SpringBootApplication
public class CitiesWebApplication {
	/**
	 * Entry point.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CitiesWebApplication.class, args);
	}

}
