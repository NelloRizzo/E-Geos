package corso.java.businessmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corso.java.businessmodel.Region;

/**
 * Regions repository.
 * 
 * @author nello
 *
 */

@Repository
public interface RegionsRepository extends JpaRepository<Region, Long> {

}