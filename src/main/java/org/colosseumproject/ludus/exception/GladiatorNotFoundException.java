package org.colosseumproject.ludus.exception;

public class GladiatorNotFoundException extends RuntimeException {

	public GladiatorNotFoundException(Integer id) {
		super("Gladiator #" + id + " could not be found.");
	}

	public GladiatorNotFoundException(String name) {
		super("Gladiator '" + name + "' could not be found.");
	}

}
