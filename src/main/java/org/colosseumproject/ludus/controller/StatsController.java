package org.colosseumproject.ludus.controller;

import java.util.HashMap;
import java.util.Map;

import org.colosseumproject.ludus.model.DuelResult;
import org.colosseumproject.ludus.model.Gladiator;
import org.colosseumproject.ludus.model.GladiatorType;
import org.colosseumproject.ludus.repository.DuelResultRepository;
import org.colosseumproject.ludus.repository.GladiatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private GladiatorRepository gladiators;

	@Autowired
	private DuelResultRepository duelResults;

	@GetMapping("gladiators")
	@JsonView()
	ResponseEntity<String> gladiatorRank() throws JsonProcessingException {
		Map<Integer, Map<String, Integer>> stats = new HashMap<Integer, Map<String, Integer>>();

		for (Gladiator g : gladiators.findAll()) {
			Map<String, Integer> typeStats = new HashMap<String, Integer>() {
				{
					put("duel_victory_count", 0);
					put("duel_defeat_count", 0);
				}
			};
			stats.put(g.getId(), typeStats);
		}

		for (DuelResult dr : duelResults.findAll()) {
			if (dr.getFirstGladiator().getId() == dr.getWinner().getId()) {
				stats.get(dr.getFirstGladiator().getId()).put("duel_victory_count",
						stats.get(dr.getFirstGladiator().getId()).get("duel_victory_count") + 1);
				stats.get(dr.getSecondGladiator().getId()).put("duel_defeat_count",
						stats.get(dr.getSecondGladiator().getId()).get("duel_defeat_count") + 1);
			} else {
				stats.get(dr.getFirstGladiator().getId()).put("duel_defeat_count",
						stats.get(dr.getFirstGladiator().getId()).get("duel_defeat_count") + 1);
				stats.get(dr.getSecondGladiator().getId()).put("duel_victory_count",
						stats.get(dr.getSecondGladiator().getId()).get("duel_victory_count") + 1);
			}
		}

		Integer ratio_multiplier = 100;
		for (Gladiator g : gladiators.findAll()) {
			Double v = Double.valueOf(stats.get(g.getId()).get("duel_victory_count"));
			Double d = Double.valueOf(stats.get(g.getId()).get("duel_defeat_count"));
			Integer ratio = (int) (v / d * ratio_multiplier);
			stats.get(g.getId()).put("ratio", ratio);
			stats.get(g.getId()).put("ratio_multiplier", ratio_multiplier);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String jacksonData = objectMapper.writeValueAsString(stats);
		return ResponseEntity.ok(jacksonData);
	}

	@GetMapping("gladiator-types")
	@JsonView()
	ResponseEntity<String> statsGladiatorTypes() throws JsonProcessingException {
		Map<GladiatorType, Map<String, Integer>> stats = new HashMap<GladiatorType, Map<String, Integer>>();

		for (GladiatorType gt : GladiatorType.values()) {
			Map<String, Integer> typeStats = new HashMap<String, Integer>() {
				{
					put("duel_victory_count", 0);
					put("duel_defeat_count", 0);
				}
			};
			stats.put(gt, typeStats);
		}

		for (DuelResult dr : duelResults.findAll()) {
			GladiatorType firstGladiatorType = dr.getFirstGladiator().getType();
			GladiatorType SecondGladiatorType = dr.getSecondGladiator().getType();
			if (firstGladiatorType != SecondGladiatorType) { // Ignore same GladiatorType duels in stats
				if (dr.getFirstGladiator().getId() == dr.getWinner().getId()) {
					stats.get(firstGladiatorType).put("duel_victory_count",
							stats.get(firstGladiatorType).get("duel_victory_count") + 1);
					stats.get(SecondGladiatorType).put("duel_defeat_count",
							stats.get(SecondGladiatorType).get("duel_defeat_count") + 1);
				} else {
					stats.get(firstGladiatorType).put("duel_defeat_count",
							stats.get(firstGladiatorType).get("duel_defeat_count") + 1);
					stats.get(SecondGladiatorType).put("duel_victory_count",
							stats.get(SecondGladiatorType).get("duel_victory_count") + 1);
				}
			}
		}

		Integer ratio_multiplier = 100;
		for (GladiatorType gt : GladiatorType.values()) {
			Double v = Double.valueOf(stats.get(gt).get("duel_victory_count"));
			Double d = Double.valueOf(stats.get(gt).get("duel_defeat_count"));
			Integer ratio = (int) (v / d * ratio_multiplier);
			stats.get(gt).put("ratio", ratio);
			stats.get(gt).put("ratio_multiplier", ratio_multiplier);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String jacksonData = objectMapper.writeValueAsString(stats);
		return ResponseEntity.ok(jacksonData);
	}
}
