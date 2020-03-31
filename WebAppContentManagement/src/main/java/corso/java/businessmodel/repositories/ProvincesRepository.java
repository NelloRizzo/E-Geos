package corso.java.businessmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corso.java.businessmodel.Province;

/**
 * Provinces repository.
 * 
 * @author nello
 *
 */
@Repository
public interface ProvincesRepository extends JpaRepository<Province, Long> {

}