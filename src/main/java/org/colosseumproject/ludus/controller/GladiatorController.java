package org.colosseumproject.ludus.controller;

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

import java.util.List;

import org.colosseumproject.ludus.domain.Gladiator;
import org.colosseumproject.ludus.repository.GladiatorRepository;
import org.colosseumproject.ludus.exception.GladiatorNotFoundException;
import org.colosseumproject.ludus.exception.GladiatorAlreadyExistsException;

@RestController
@RequestMapping("/gladiators")
public class GladiatorController {

	@Autowired
	private GladiatorRepository gladiators;

	@GetMapping("")
	ResponseEntity<List<Gladiator>> findAll() {
		return ResponseEntity.ok(gladiators.findAll());
	}

	@GetMapping("/{id}")
	ResponseEntity<Gladiator> findOne(@PathVariable Integer id) {
		return ResponseEntity.ok(gladiators.findById(id)
				.orElseThrow(() -> new GladiatorNotFoundException(id)));
	}

	@GetMapping("/name/{name}")
	ResponseEntity<Gladiator> findOneByName(@PathVariable String name) {
		Gladiator gladiator = gladiators.findByName(name);
		if (gladiator == null) {
			throw new GladiatorNotFoundException(name);
		}
		return ResponseEntity.ok(gladiator);
	}

	@PostMapping("")
	ResponseEntity<Gladiator> newOne(@RequestBody Gladiator gladiator) {
		String name = gladiator.getName();
		if (gladiators.findByName(name) != null) {
			throw new GladiatorAlreadyExistsException(name);
		}
		return ResponseEntity.ok(gladiators.save(gladiator));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Gladiator> deleteOne(@PathVariable Integer id) {
		return gladiators.findById(id)
				.map(gladiator -> {
					gladiators.deleteById(gladiator.getId());
					return ResponseEntity.ok(gladiator);
				})
				.orElseThrow(() -> new GladiatorNotFoundException(id));
	}

	@PutMapping("/{id}")
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

}
