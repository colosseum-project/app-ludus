package org.colosseumproject.ludus.view;

public class DuelResultViews {

	public interface IdOnly extends GladiatorViews.IdOnly {
	}

	public interface Summary extends IdOnly, GladiatorViews.Summary {
	}

	public interface Detailed extends Summary {
	}

}
