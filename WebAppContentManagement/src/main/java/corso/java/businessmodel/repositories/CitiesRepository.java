package corso.java.businessmodel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corso.java.businessmodel.City;

/**
 * Cities repository.
 * 
 * @author nello
 *
 */
@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
	@Query("select city from City city where upper(city.province.region.name) = upper(:region)")
	Page<City> citiesOfRegion(Pageable pageable, @Param(value = "region") String region);

	@Query("select city from City city where upper(city.province.name) = upper(:province) or upper(city.province.acronym) = upper(:province)")
	Page<City> citiesOfProvince(Pageable pageable, @Param(value = "province") String province);

	@Query("select city from City city where upper(city.name) = upper(:name)")
	Page<City> findByName(Pageable pageable, @Param(value = "name") String name);

	@Query("select city from City city where upper(city.name) = upper(:city) and upper(city.province.name) = upper(:province)")
	Page<City> findByNameAndProvince(Pageable pageable, @Param(value = "city") String city,
			@Param(value = "province") String province);
}