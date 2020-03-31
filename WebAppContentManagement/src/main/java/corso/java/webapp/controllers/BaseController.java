package corso.java.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import corso.java.businessmodel.repositories.AreasRepository;
import corso.java.businessmodel.repositories.CitiesRepository;
import corso.java.businessmodel.repositories.ProvincesRepository;
import corso.java.businessmodel.repositories.RegionsRepository;

public class BaseController {
	@Autowired
	protected AreasRepository areas;
	@Autowired
	protected CitiesRepository cities;
	@Autowired
	protected ProvincesRepository provinces;
	@Autowired
	protected RegionsRepository regions;

}
