package org.colosseumproject.ludus.exception;

public class APIErrorException extends RuntimeException {

	public APIErrorException(String message) {
		super("API call error: " + message);
	}

}
