package org.colosseumproject.ludus.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.colosseumproject.ludus.repository.GladiatorRepository;
import org.colosseumproject.ludus.view.GladiatorViews;
import org.colosseumproject.ludus.exception.GladiatorNotFoundException;
import org.colosseumproject.ludus.model.Gladiator;
import org.colosseumproject.ludus.model.GladiatorType;
import org.colosseumproject.ludus.exception.GladiatorAlreadyExistsException;

@RestController
@RequestMapping("/gladiators")
public class GladiatorController {

	@Autowired
	private GladiatorRepository gladiators;

	private Gladiator findOne(Integer id) {
		return gladiators.findById(id).orElseThrow(() -> new GladiatorNotFoundException(id));
	}

	private Gladiator findOneByName(String name) {
		Gladiator gladiator = gladiators.findByName(name);
		if (gladiator == null) {
			throw new GladiatorNotFoundException(name);
		}
		return gladiator;
	}

	@GetMapping("")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<List<Gladiator>> findAll() {
		return ResponseEntity.ok(gladiators.findAll());
	}

	@GetMapping("/{id}")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<Gladiator> findOneSummary(@PathVariable Integer id) {
		return ResponseEntity.ok(findOne(id));
	}

	@GetMapping("/{id}/attributes")
	@JsonView(GladiatorViews.Attributes.class)
	ResponseEntity<Gladiator> findOneAttributes(@PathVariable Integer id) {
		return ResponseEntity.ok(findOne(id));
	}

	@GetMapping("/{id}/equipment")
	@JsonView(GladiatorViews.Equipment.class)
	ResponseEntity<Gladiator> findOneEquipment(@PathVariable Integer id) {
		return ResponseEntity.ok(findOne(id));
	}

	@GetMapping("/{id}/full")
	ResponseEntity<Gladiator> findOneFull(@PathVariable Integer id) {
		return ResponseEntity.ok(findOne(id));
	}

	@GetMapping("/name/{name}")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<Gladiator> findOneByNameSummary(@PathVariable String name) {
		return ResponseEntity.ok(findOneByName(name));
	}

	@GetMapping("/name/{name}/attributes")
	@JsonView(GladiatorViews.Attributes.class)
	ResponseEntity<Gladiator> findOneByNameAttributes(@PathVariable String name) {
		return ResponseEntity.ok(findOneByName(name));
	}

	@GetMapping("/name/{name}/equipment")
	@JsonView(GladiatorViews.Equipment.class)
	ResponseEntity<Gladiator> findOneByNameEquipment(@PathVariable String name) {
		return ResponseEntity.ok(findOneByName(name));
	}

	@GetMapping("/name/{name}/full")
	ResponseEntity<Gladiator> findOneByNameFull(@PathVariable String name) {
		return ResponseEntity.ok(findOneByName(name));
	}

	@PostMapping("")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<Gladiator> newOne(@RequestBody Gladiator gladiator) {
		String name = gladiator.getName();
		if (gladiators.findByName(name) != null) {
			throw new GladiatorAlreadyExistsException(name);
		}
		return ResponseEntity.ok(gladiators.save(gladiator));
	}

	@DeleteMapping("/{id}")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<Gladiator> deleteOne(@PathVariable Integer id) {
		return gladiators.findById(id)
				.map(gladiator -> {
					gladiators.deleteById(gladiator.getId());
					return ResponseEntity.ok(gladiator);
				})
				.orElseThrow(() -> new GladiatorNotFoundException(id));
	}

	@PutMapping("/{id}")
	@JsonView(GladiatorViews.Summary.class)
	ResponseEntity<Gladiator> editOne(@PathVariable Integer id, @RequestBody Gladiator newGladiator) {
		return gladiators.findById(id)
				.map(gladiator -> {
					String name = newGladiator.getName();
					if (gladiators.findByName(name) != null) {
						throw new GladiatorAlreadyExistsException(name);
					}
					gladiator.setName(name);
					return ResponseEntity.ok(gladiators.save(gladiator));
				})
				.orElseThrow(() -> new GladiatorNotFoundException(id));
	}

	@GetMapping("/count")
	ResponseEntity<Long> count() {
		return ResponseEntity.ok(gladiators.count());
	}

	@GetMapping("/types")
	ResponseEntity<GladiatorType[]> findAllTypes() {
		return ResponseEntity.ok(GladiatorType.values());
	}

}
