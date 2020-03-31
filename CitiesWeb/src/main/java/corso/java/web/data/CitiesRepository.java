package corso.java.web.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementazione di un repository di città.
 * 
 * @author nello
 *
 */
@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {

}