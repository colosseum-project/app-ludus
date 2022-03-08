package org.colosseumproject.ludus.exception;

public class DuelAlreadyResolvedException extends RuntimeException {

	public DuelAlreadyResolvedException() {
		super("Duel has been already resolved.");
	}

}
