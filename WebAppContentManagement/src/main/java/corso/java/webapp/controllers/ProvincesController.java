package corso.java.webapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.businessmodel.Province;

@RestController
@RequestMapping("/api/v1")
public class ProvincesController extends BaseController {
	@GetMapping("/provinces")
	public ResponseEntity<List<Province>> getProvinces() {
		try {
			return ResponseEntity.ok().body(provinces.findAll().stream()
					.sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList()));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
