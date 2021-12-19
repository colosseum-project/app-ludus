package org.colosseumproject.ludus.model;

class WeaponDamage {

	private Integer minimum;
	private Integer maximum;
	private Integer criticalPercentage;

	WeaponDamage(Integer minimum, Integer maximum, Integer criticalPercentage) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.criticalPercentage = criticalPercentage;
	}

	Integer getMinimum() {
		return minimum;
	}

	Integer getMaximum() {
		return maximum;
	}

	Integer getCriticalPercentage() {
		return criticalPercentage;
	}

}
