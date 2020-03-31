package corso.java.webapp.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.businessmodel.City;
import corso.java.businessmodel.Gender;
import corso.java.businessmodel.Location;
import corso.java.businessmodel.PersonalData;
import corso.java.webmodel.PersonModel;

@RestController
@RequestMapping("/api/v1")
public class ServicesController extends BaseController {

	@PostMapping("/fiscalcode")
	public ResponseEntity<PersonalData> fiscalCode(@RequestBody PersonModel data) {
		City city = data.getBirthProvince() != null
				? cities.findByNameAndProvince(PageRequest.of(0, 1), data.getBirthCity(), data.getBirthProvince()).get()
						.findFirst().orElse(null)
				: cities.findByName(PageRequest.of(0, 1), data.getBirthCity()).get().findFirst().orElse(null);
		String placeName = data.getBirthCity();
		if (data.getBirthProvince() != null)
			placeName += "," + data.getBirthProvince();
		if (city.getLocation() == null) {
			city.setLocation(Location.forCity(placeName));
			cities.save(city);
		}
		PersonalData p = new PersonalData(data.getName(), data.getSurname(), data.getBirthday(),
				Gender.fromChar(data.getGender().charAt(0)), city);
		return ResponseEntity.ok().body(p);
	}
}
