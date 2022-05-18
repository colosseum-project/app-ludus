package org.colosseumproject.ludus.service;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.colosseumproject.ludus.exception.APIErrorException;
import org.colosseumproject.ludus.model.DuelResult;
import org.colosseumproject.ludus.model.Gladiator;

public class ArenaAPI extends GenericAPI {

	public ArenaAPI() {
		endpoint = "http://localhost:8082";
	}

	public DuelResult resolveDuel(Gladiator firstGladiator, Gladiator secondGladiator) {
		String requestJson, response;
		try {
			ArrayList<Gladiator> gladiators = new ArrayList<Gladiator>();
			gladiators.add(firstGladiator);
			gladiators.add(secondGladiator);
			requestJson = new ObjectMapper().writeValueAsString(gladiators);
		} catch (JsonProcessingException e) {
			throw new APIErrorException("JSON processing exception: " + e.getMessage());
		}

		response = postWithBody("/duel", requestJson);

		DuelResult duelResult = new DuelResult();
		int winnerId;
		try {
			ObjectMapper mapper = new ObjectMapper();

			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			duelResult = mapper.readValue(response, DuelResult.class);

			JsonNode tree = mapper.readTree(response);
			winnerId = tree.path("winnerId").asInt();
		} catch (JsonProcessingException e) {
			throw new APIErrorException("JSON processing exception: " + e.getMessage());
		} catch (Exception e) {
			throw new APIErrorException(e.getMessage());
		}
		duelResult.setFirstGladiator(firstGladiator);
		duelResult.setSecondGladiator(secondGladiator);
		if (winnerId == firstGladiator.getId()) {
			duelResult.setWinner(firstGladiator);
		} else {
			duelResult.setWinner(secondGladiator);
		}
		duelResult.updateTimestamp();

		return duelResult;
	}

}
