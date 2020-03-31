package corso.java.web.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import corso.java.web.data.CitiesRepository;
import corso.java.web.data.City;

@Controller(value = "web-cities")
public class CitiesController {
	private static final int MIN_PAGESIZE = 10;
	private static final int MAX_PAGESIZE = 100;
	/**
	 * Gestore della persistenza.
	 */
	@Autowired
	private CitiesRepository citiesRepository;

	/**
	 * Gestisce la vista sull'elenco delle città.
	 * 
	 * @param model il Model.
	 * @return restituisce il nome della vista da visualizzare.
	 */
	@GetMapping("/cities")
	public String citiesList(Model model, HttpServletRequest request) {
		int page = 0;
		int pageSize = 30;
		try {
			page = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"));
			pageSize = request.getParameter("pageSize") == null ? 30
					: Integer.parseInt(request.getParameter("pageSize"));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		long count = citiesRepository.count();
		long totalPages = (long) Math.round((float) count / pageSize + .5);

		if (page < 0)
			page = 0;
		if (page > totalPages)
			page = (int)totalPages - 1;
		if (pageSize < MIN_PAGESIZE)
			pageSize = MIN_PAGESIZE;
		if (pageSize > MAX_PAGESIZE)
			pageSize = MAX_PAGESIZE;
		Pageable pageable = PageRequest.of(page, pageSize);

		Page<City> cities = citiesRepository.findAll(pageable);
		model.addAttribute("cities", cities);
		model.addAttribute("count", count);
		model.addAttribute("previousPage", page == 0 ? 0 : page - 1);
		model.addAttribute("nextPage", page < totalPages - 1 ? page + 1 : page);
		model.addAttribute("lastPage", totalPages - 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		return "cities_list";
	}
}
