package org.colosseumproject.ludus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import org.colosseumproject.ludus.exception.DuelErrorException;
import org.colosseumproject.ludus.exception.GladiatorNotFoundException;
import org.colosseumproject.ludus.model.Duel;
import org.colosseumproject.ludus.model.DuelResult;
import org.colosseumproject.ludus.repository.DuelResultRepository;
import org.colosseumproject.ludus.repository.GladiatorRepository;
import org.colosseumproject.ludus.view.DuelResultViews;

@RestController
@RequestMapping("/duels")
public class DuelController {

	@Autowired
	private GladiatorRepository gladiators;

	@Autowired
	private DuelResultRepository duelResults;

	@PostMapping("resolve")
	@JsonView(DuelResultViews.Default.class)
	ResponseEntity<DuelResult> engage(
			@RequestParam("first_gladiator_id") Integer firstGladiatorId,
			@RequestParam("second_gladiator_id") Integer secondGladiatorId) {
		Duel duel = new Duel(
				gladiators.findById(firstGladiatorId)
						.orElseThrow(() -> new GladiatorNotFoundException(firstGladiatorId)),
				gladiators.findById(secondGladiatorId)
						.orElseThrow(() -> new GladiatorNotFoundException(secondGladiatorId)));
		try {
			duel.resolve();
		} catch (Exception e) {
			throw new DuelErrorException(e.getMessage());
		}
		DuelResult duelResult = new DuelResult(
				duel.getFirstGladiator(),
				duel.getSecondGladiator(),
				duel.getWinner(),
				duel.getCombatLogs());
		return ResponseEntity.ok(duelResults.save(duelResult));
	}

}
