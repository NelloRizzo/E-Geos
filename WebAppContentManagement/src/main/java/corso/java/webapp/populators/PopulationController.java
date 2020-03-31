package corso.java.webapp.populators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.businessmodel.DataLoader;
import corso.java.businessmodel.repositories.AreasRepository;
import corso.java.businessmodel.repositories.CitiesRepository;
import corso.java.businessmodel.repositories.ProvincesRepository;
import corso.java.businessmodel.repositories.RegionsRepository;

@RestController
@RequestMapping("/api/v1")
public class PopulationController {
	@Autowired
	AreasRepository areas;
	@Autowired
	CitiesRepository cities;
	@Autowired
	ProvincesRepository provinces;
	@Autowired
	RegionsRepository regions;

	private DataLoader loader;

	public PopulationController() {
		try {
			ClassPathResource res = new ClassPathResource("public/comuni.csv");
			loader = new DataLoader().setSourceStream(res.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/populate/areas")
	public Map<String, Boolean> populateAreas() {
		Map<String, Boolean> response = new HashMap<>();
		loader.load(false, false);
		int count = loader.getAreas().size();
		areas.saveAll(loader.getAreas());
		response.put(String.format("Areas loaded (%d total)", count), Boolean.TRUE);
		return response;
	}

	@GetMapping("/populate/regions")
	public Map<String, Boolean> populateRegions() {
		Map<String, Boolean> response = new HashMap<>();
		loader.load(false, false);
		int count = loader.getRegions().size();
		regions.saveAll(loader.getRegions());
		response.put(String.format("Regions loaded (%d total)", count), Boolean.TRUE);
		return response;
	}

	@GetMapping("/populate/provinces")
	public Map<String, Boolean> populateProvinces() {
		Map<String, Boolean> response = new HashMap<>();
		loader.load(false, false);
		int count = loader.getRegions().size();
		provinces.saveAll(loader.getProvinces());
		response.put(String.format("Provinces loaded (%d total)", count), Boolean.TRUE);
		return response;
	}

	@GetMapping("/populate/cities")
	public Map<String, Boolean> populateCities() {
		Map<String, Boolean> response = new HashMap<>();
		loader.load(false, false);
		int count = loader.getCities().size();
		cities.saveAll(loader.getCities());
		response.put(String.format("Cities loaded (%d total)", count), Boolean.TRUE);
		return response;
	}
}
