package org.colosseumproject.ludus.exception;

public class GladiatorAlreadyExistsException extends RuntimeException {

	public GladiatorAlreadyExistsException(String name) {
		super("Gladiator '" + name + "' already exists.");
	}

}
