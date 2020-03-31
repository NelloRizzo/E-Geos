package corso.java.businessmodel.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories
public class PersistenceService {
	@Autowired
	AreasRepository areas;
	@Autowired
	CitiesRepository cities;
	@Autowired
	ProvincesRepository provinces;
	@Autowired
	RegionsRepository regions;

	public AreasRepository getAreas() {
		return areas;
	}

	public CitiesRepository getCities() {
		return cities;
	}

	public ProvincesRepository getProvinces() {
		return provinces;
	}

	public RegionsRepository getRegions() {
		return regions;
	}
}
