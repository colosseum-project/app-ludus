package org.colosseumproject.ludus.model;

class WeaponDamage {

	private Integer minimum;
	private Integer maximum;

	WeaponDamage(
			Integer minimum,
			Integer maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	Integer getMinimum() {
		return minimum;
	}

	Integer getMaximum() {
		return maximum;
	}

}
