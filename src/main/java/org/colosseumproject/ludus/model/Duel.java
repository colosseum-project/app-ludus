package org.colosseumproject.ludus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.colosseumproject.ludus.exception.DuelAlreadyResolvedException;
import org.colosseumproject.ludus.exception.DuelErrorException;
import org.hibernate.cfg.NotYetImplementedException;

public class Duel {

	private boolean resolved = false;
	private Gladiator firstGladiator;
	private Gladiator secondGladiator;
	private Gladiator winner;
	private List<String> combatLogs;

	public Duel(Gladiator firstGladiator, Gladiator secondGladiator) {
		if (firstGladiator.getId().equals(secondGladiator.getId())) {
			throw new DuelErrorException("Same gladiator.");
		}
		this.firstGladiator = firstGladiator;
		this.secondGladiator = secondGladiator;
	}

	public void resolve() throws Exception {
		String arenaJsonResponse = mockCallArenaAndGetResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> arenaResponse = objectMapper.readValue(arenaJsonResponse,
				new TypeReference<Map<String, Object>>() {
				});
		String winnerIdFromResponse = arenaResponse.get("winner_id").toString();
		if (winnerIdFromResponse.equals(firstGladiator.getId().toString())) {
			winner = firstGladiator;
		} else if (winnerIdFromResponse.equals(secondGladiator.getId().toString())) {
			winner = secondGladiator;
		} else {
			throw new Exception("Winner ID from response is incorrect.");
		}
		if (arenaResponse.get("logs") instanceof ArrayList) {
			@SuppressWarnings("unchecked")
			List<String> logs = (List<String>) arenaResponse.get("logs");
			combatLogs = logs;
		}
		resolved = true;
	}

	private void callArenaAndGetResponse() {
		throw new NotYetImplementedException();
	}

	private String mockCallArenaAndGetResponse() throws JsonProcessingException {
		if (resolved) {
			throw new DuelAlreadyResolvedException();
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("winner_id", firstGladiator.getId().toString());
		String[] log = {
				firstGladiator.getName() + " hit " + secondGladiator.getName() + " so hard!",
				secondGladiator.getName() + "'s blow was dodged...",
				firstGladiator.getName() + " gave the fatal blow and defeated his opponent."
		};
		response.put("logs", log);
		return new ObjectMapper().writeValueAsString(response);
	}

	public boolean IsResolved() {
		return resolved;
	}

	public Gladiator getFirstGladiator() {
		return firstGladiator;
	}

	public Gladiator getSecondGladiator() {
		return secondGladiator;
	}

	public Gladiator getWinner() {
		return winner;
	}

	public List<String> getCombatLogs() {
		return combatLogs;
	}

}
