package org.colosseumproject.ludus.exception;

public class DuelResultNotFoundException extends RuntimeException {

	public DuelResultNotFoundException(Integer id) {
		super("Duel result #" + id + " could not be found.");
	}

}
