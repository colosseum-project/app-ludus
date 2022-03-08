package org.colosseumproject.ludus.exception;

public class DuelErrorException extends RuntimeException {

	public DuelErrorException(String message) {
		super("Duel cannot be resolved: " + message);
	}

}
