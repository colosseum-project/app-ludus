package org.colosseumproject.ludus.model;

import org.colosseumproject.ludus.exception.DuelErrorException;
import org.colosseumproject.ludus.service.ArenaAPI;

public class Duel {

	private boolean resolved = false;
	private Gladiator firstGladiator;
	private Gladiator secondGladiator;
	private Gladiator winner;
	private DuelResult duelResult;

	public Duel(Gladiator firstGladiator, Gladiator secondGladiator) {
		if (firstGladiator.getId().equals(secondGladiator.getId())) {
			throw new DuelErrorException("Same gladiator.");
		}
		this.firstGladiator = firstGladiator;
		this.secondGladiator = secondGladiator;
	}

	public void resolve() throws Exception {
		ArenaAPI api = new ArenaAPI();
		duelResult = api.resolveDuel(firstGladiator, secondGladiator);
		duelResult.setFirstGladiator(firstGladiator);
		duelResult.setSecondGladiator(secondGladiator);
		resolved = true;
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

	public DuelResult getDuelResult() {
		return duelResult;
	}

}
