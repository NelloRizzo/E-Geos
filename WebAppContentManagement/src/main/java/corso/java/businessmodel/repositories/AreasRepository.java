package corso.java.businessmodel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corso.java.businessmodel.Area;
import corso.java.businessmodel.Province;

/**
 * Areas repository.
 * 
 * @author nello
 *
 */
@Repository
public interface AreasRepository extends JpaRepository<Area, Long> {

	@Query("select p from Province p where p.region.area.id = :id")
	Page<Province> provincesByArea(Pageable pageable, @Param(value = "id") Long areaId);
}