package org.colosseumproject.ludus.model;

import java.util.List;

public enum GladiatorType {

	MURMILLO(
			"Murmillo", Weapon.GLADIUS,
			List.of(ArmourComponent.SCUTUM,
					ArmourComponent.CASSISCRISTA,
					ArmourComponent.MANICAE,
					ArmourComponent.OCREA)),
	THRAEX(
			"Thraex", Weapon.SICA,
			List.of(ArmourComponent.PARMA,
					ArmourComponent.GALEA,
					ArmourComponent.MANICAE,
					ArmourComponent.OCREA)),
	DIMACHAERUS(
			"Dimachaerus", Weapon.DUALGLADIUS,
			List.of(ArmourComponent.BALTEUS));

	private String name;
	private Weapon weapon;
	private List<ArmourComponent> armourSet;

	GladiatorType(String name, Weapon weapon, List<ArmourComponent> armourSet) {
		this.name = name;
		this.weapon = weapon;
		this.armourSet = armourSet;
	}

	String getName() {
		return name;
	}

	Weapon getWeapon() {
		return weapon;
	}

	List<ArmourComponent> getArmourSet() {
		return armourSet;
	}

}
