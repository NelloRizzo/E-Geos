package corso.java.webapp.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.businessmodel.Area;
import corso.java.businessmodel.Province;
import corso.java.businessmodel.Region;

@RestController
@RequestMapping("/api/v1")
public class AreasController extends BaseController {
	public long getCount() {
		return areas.count();
	}

	@GetMapping("/areas")
	public List<Area> getList(@RequestHeader(value = "page", required = false, defaultValue = "-1") int page,
			@RequestHeader(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		try {
			Pageable pageable = page < 0 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

			Page<Area> result = areas.findAll(pageable);
			return result.getContent();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}

	@GetMapping("/areas/{id}")
	public ResponseEntity<Area> get(@PathVariable(value = "id") long id) {
		try {
			Area area = areas.getOne(id);
			return ResponseEntity.ok(area);
		} catch (EntityNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@PostMapping("/areas")
	public ResponseEntity<Area> add(@RequestBody Area area) {
		try {
			area = areas.save(area);
			return ResponseEntity.created(URI.create("/areas/" + area.getId())).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@GetMapping("/areas/{id}/regions")
	public List<Region> regionsByArea(@PathVariable(name = "id", required = true) Long id) {
		try {
			return regions.findAll().stream().filter(r -> r.getArea().getId() == id).collect(Collectors.toList());
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Region>();
		}

	}

	@GetMapping("/areas/{id}/provinces")
	public List<Province> provincesByArea(@PathVariable(name = "id", required = true) Long id) {
		try {
			return areas.provincesByArea(Pageable.unpaged(), id).getContent();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Province>();
		}

	}
}
