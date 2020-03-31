package corso.java.webapp.controllers.mvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import corso.java.businessmodel.City;
import corso.java.businessmodel.Gender;
import corso.java.businessmodel.PersonalData;
import corso.java.webapp.controllers.BaseController;
import corso.java.webmodel.FiscalCodeViewModel;

@Controller(value = "mvcServices")
@RequestMapping("/")
public class ServicesController extends BaseController {

	private final String VIEW_INDEX = "index";

	@GetMapping
	public String index(Model model) {
		FiscalCodeViewModel vm = new FiscalCodeViewModel();
		model.addAttribute("viewModel", vm);
		return VIEW_INDEX;
	}

	@PostMapping("/fiscalcodecreator")
	public String createFiscalCode(@ModelAttribute FiscalCodeViewModel vm, Model model) {
		vm.clearErrors();
		Date birthday = new Date();
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(vm.getBirthday());
		} catch (ParseException e) {
			e.printStackTrace();
			vm.addError("Data non valida.");
		}
		if (vm.getBirthCity() == null || vm.getBirthCity().length() == 0)
			vm.addError("La città non può essere vuota.");
		if (vm.getName() == null || vm.getName().length() == 0)
			vm.addError("Il nome non può essere vuoto.");
		if (vm.getSurname() == null || vm.getSurname().length() == 0)
			vm.addError("Il cognome non può essere vuoto.");
		if (vm.isValid()) {
			City city = cities.findByName(PageRequest.of(0, 1), vm.getBirthCity()).stream().findFirst().orElse(null);
			PersonalData data = new PersonalData().setBirthCity(city).setBirthday(birthday)
					.setGender(Gender.fromChar(vm.getGender().charAt(0))).setName(vm.getName())
					.setSurname(vm.getSurname());
			vm.setFiscalCode(data.getFiscalCode());
		}
		model.addAttribute("viewModel", vm);
		return VIEW_INDEX;
	}
}
