package org.colosseumproject.ludus.model;

class WeaponCriticalHit {

	private Integer chancePercentage;
	private Double multiplier;

	WeaponCriticalHit(
			Integer chancePercentage,
			Double multiplier) {
		this.chancePercentage = chancePercentage;
		this.multiplier = multiplier;
	}

	Integer getChancePercentage() {
		return chancePercentage;
	}

	Double getMultiplier() {
		return multiplier;
	}

}
