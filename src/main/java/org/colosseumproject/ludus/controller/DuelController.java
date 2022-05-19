package org.colosseumproject.ludus.controller;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.colosseumproject.ludus.exception.DuelErrorException;
import org.colosseumproject.ludus.exception.DuelResultNotFoundException;
import org.colosseumproject.ludus.exception.GladiatorNotFoundException;
import org.colosseumproject.ludus.model.DuelResult;
import org.colosseumproject.ludus.model.Gladiator;
import org.colosseumproject.ludus.repository.DuelResultRepository;
import org.colosseumproject.ludus.repository.GladiatorRepository;
import org.colosseumproject.ludus.service.ArenaAPI;
import org.colosseumproject.ludus.view.DuelResultViews;

@RestController
@RequestMapping("/duels")
public class DuelController {

	@Autowired
	private GladiatorRepository gladiators;

	@Autowired
	private DuelResultRepository duelResults;

	@Autowired
	private ArenaAPI arenaApi;

	ResponseEntity<DuelResult> resolve(Gladiator firstGladiator, Gladiator secondGladiator) {
		if (firstGladiator.getId().equals(secondGladiator.getId())) {
			throw new DuelErrorException("Same gladiator.");
		}
		DuelResult duelResult = arenaApi.resolveDuel(firstGladiator, secondGladiator);
		duelResult.setFirstGladiator(firstGladiator);
		duelResult.setSecondGladiator(secondGladiator);
		return ResponseEntity.ok(duelResults.save(duelResult));
	}

	@PostMapping("resolve")
	@JsonView(DuelResultViews.Detailed.class)
	ResponseEntity<DuelResult> resolveChoice(
			@RequestParam("first_gladiator_id") Integer firstGladiatorId,
			@RequestParam("second_gladiator_id") Integer secondGladiatorId) {
		return resolve(
				gladiators.findById(firstGladiatorId)
						.orElseThrow(() -> new GladiatorNotFoundException(firstGladiatorId)),
				gladiators.findById(secondGladiatorId)
						.orElseThrow(() -> new GladiatorNotFoundException(secondGladiatorId)));
	}

	@PostMapping("resolve/random")
	@JsonView(DuelResultViews.Detailed.class)
	ResponseEntity<DuelResult> resolveRandom() {
		if (gladiators.count() < 2) {
			throw new DuelErrorException("Not enough registered gladiators to engage a random duel.");
		}
		List<Gladiator> g = gladiators.findAll();
		Collections.shuffle(g);
		return resolve(g.remove(0), g.remove(0));
	}

	@GetMapping("results")
	@JsonView(DuelResultViews.Summary.class)
	ResponseEntity<List<DuelResult>> findAll() {
		return ResponseEntity.ok(duelResults.findAll());
	}

	@GetMapping("results/{id}")
	@JsonView(DuelResultViews.Detailed.class)
	ResponseEntity<DuelResult> findOne(@PathVariable Integer id) {
		return ResponseEntity.ok(duelResults.findById(id).orElseThrow(() -> new DuelResultNotFoundException(id)));
	}

}
