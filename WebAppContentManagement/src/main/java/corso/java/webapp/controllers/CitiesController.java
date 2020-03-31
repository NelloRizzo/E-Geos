package corso.java.webapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.businessmodel.City;
import corso.java.businessmodel.Location;

@RestController
@RequestMapping("/api/v1")
public class CitiesController extends BaseController {
	@GetMapping("/cities/of/region/{region}")
	public ResponseEntity<List<City>> citiesOfRegion(@PathVariable(value = "region") String region,
			@RequestHeader(value = "page", required = false, defaultValue = "-1") int page,
			@RequestHeader(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		try {
			Pageable pageable = page < 0 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

			return ResponseEntity.ok(cities.citiesOfRegion(pageable, region).getContent());
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/cities/of/province/{province}")
	public ResponseEntity<List<City>> citiesOfProvince(@PathVariable(value = "province") String province,
			@RequestHeader(value = "page", required = false, defaultValue = "-1") int page,
			@RequestHeader(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		try {
			Pageable pageable = page < 0 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

			return ResponseEntity.ok(cities.citiesOfProvince(pageable, province).getContent().stream()
					.sorted((c1, c2) -> c1.getName().compareTo(c2.getName())).collect(Collectors.toList()));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/cities/{name}/{province}")
	public ResponseEntity<City> getCityById(@PathVariable(value = "name") String cityName,
			@PathVariable(value = "province", required = false) String acronym) throws ResourceNotFoundException {
		try {
			Predicate<? super City> pc = acronym == null ? c -> c.getName().equals(cityName)
					: c -> c.getName().equalsIgnoreCase(cityName)
							&& (c.getProvince().getName().equalsIgnoreCase(acronym)
									|| c.getProvince().getAcronym().equalsIgnoreCase(acronym));
			String placeName = cityName + ((acronym == null) ? "" : "," + acronym);
			Optional<City> city = cities.findAll().stream().filter(pc).findFirst();
			if (city.isPresent()) {
				City result = city.get();
				result.setLocation(Location.forCity(placeName));
				return ResponseEntity.ok().header("GoogleLink", "https://maps.google.it/?q=" + placeName).body(result);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
