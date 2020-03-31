package corso.java.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.business.model.GeographicDecorator;
import corso.java.business.model.Place;
import corso.java.business.model.Position;
import corso.java.web.data.CitiesRepository;
import corso.java.web.data.City;

/**
 * Accesso al repository dei comuni.
 * 
 * @author nello
 *
 */
@RestController
@RequestMapping("/api/v1")
public class CitiesController {
	/**
	 * Gestore della persistenza.
	 */
	@Autowired
	private CitiesRepository citiesRepository;

	/**
	 * Popola il database delle città.
	 * 
	 * @return un messaggio indicativo dell'operazione effettuata.
	 */
	@GetMapping("/populate/cities")
	public Map<String, Boolean> Populate() {
		Map<String, Boolean> response = new HashMap<>();
		try {
			InputStream is = new ClassPathResource("public/comuni.csv").getInputStream();
			List<City> cities = new corso.java.business.model.CitiesStreamReader(is)
					.process().stream().map(c -> new City(c.getId(), c.getName(), c.getProvince(), c.getAcronym(),
							c.isCapital(), c.getRegion(), c.getArea(), c.getFiscalCode(), c.getPeople()))
					.collect(Collectors.toList());
			int count = cities.size();
			citiesRepository.saveAll(cities);
			response.put(String.format("done: %d cities added.", count), Boolean.TRUE);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			response.put("done", Boolean.FALSE);
		} catch (IOException e) {
			e.printStackTrace();
			response.put("done", Boolean.FALSE);
		}
		return response;
	}

	/**
	 * Ottiene l'elenco delle città.
	 * 
	 * @return l'elenco delle città.
	 */
	@GetMapping("/cities")
	public List<City> getAllCities() {
		return citiesRepository.findAll();
	}

	/**
	 * Recupera una città in base alla sua chiave.
	 * 
	 * @param cityId chiave.
	 * @return Restituisce la città che corrisponde alla chiave passata.
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/cities/{id}")
	public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId) throws ResourceNotFoundException {
		City city = citiesRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));
		return ResponseEntity.ok().body(city);
	}

	/**
	 * Ricerca un elenco di città sulla base di un nome parziale.
	 * 
	 * @param name stringa da cercare.
	 * @return l'elenco delle città che soddisfano i criteri di ricerca.
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/cities/byname/{name}")
	@Query("select new City(id,name,province,acronym,capital,region,area,fiscalCode,people) from City c where b.name like ?1 ")
	public List<City> getCityByName(@PathVariable(value = "name") String name) throws ResourceNotFoundException {
		return citiesRepository.findAll();
	}

	/**
	 * Ottiene i riferimenti geografici della città identificata dalla chiave.
	 * 
	 * @param id chiave della città da cercare.
	 * @return le coordinate geografiche della città richiesta.
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("cities/{id}/geo")
	public Place getPosition(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		GeographicDecorator result = null;
		try {
			City target = citiesRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
			URL url = new URL("https://maps.google.it/?q=" + target.getName() + "," + target.getRegion());
			URLConnection conn = url.openConnection();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				List<String> strings = br.lines().collect(Collectors.toList());
				String page = String.join(" ", strings);
				Pattern pattern = Pattern.compile(
						"window.APP_INITIALIZATION_STATE=\\[\\[\\[(?<z>\\d+\\.\\d+),(?<longitude>\\d+\\.\\d+),(?<latitude>\\d+\\.\\d+)\\]");
				Matcher m = pattern.matcher(page);
				if (m.find()) {
					GeographicDecorator gd = new GeographicDecorator(
							new corso.java.business.model.City(id, target.getName(), target.getProvince(),
									target.getAcronym(), target.isCapital(), target.getRegion(), target.getArea(),
									target.getFiscalCode(), target.getPeople()),
							new Position(Float.parseFloat(m.group("latitude")),
									Float.parseFloat(m.group("longitude"))));
					return gd;
				}
			}
			catch (IOException ex) {
				return new corso.java.business.model.City(id, target.getName(), target.getProvince(),
						target.getAcronym(), target.isCapital(), target.getRegion(), target.getArea(),
						target.getFiscalCode(), target.getPeople());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Salva una città.
	 * 
	 * @param city città da salvare.
	 * @return la città salvata.
	 */
	@PostMapping("/cities")
	public City createCity(@Valid @RequestBody City city) {
		return citiesRepository.save(city);
	}

	/**
	 * Aggiorna i dati di una città.
	 * 
	 * @param cityId      chiave della città da modificare.
	 * @param cityDetails dettagli della modifica.
	 * @return la città modificata.
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/cities/{id}")
	public ResponseEntity<City> updatecity(@PathVariable(value = "id") Long cityId,
			@Valid @RequestBody City cityDetails) throws ResourceNotFoundException {
		City city = citiesRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

		city.setAcronym(cityDetails.getAcronym());
		city.setArea(cityDetails.getArea());
		city.setCapital(cityDetails.isCapital());
		city.setFiscalCode(cityDetails.getFiscalCode());
		city.setPeople(cityDetails.getPeople());
		city.setProvince(cityDetails.getProvince());
		city.setRegion(cityDetails.getRegion());
		final City updatedCity = citiesRepository.save(city);
		return ResponseEntity.ok(updatedCity);
	}

	/**
	 * Elimina una città.
	 * 
	 * @param cityId chiave della città da eliminare.
	 * @return un valore booleano che indica il successo dell'operazione.
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/cities/{id}")
	public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) throws ResourceNotFoundException {
		City city = citiesRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

		citiesRepository.delete(city);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
