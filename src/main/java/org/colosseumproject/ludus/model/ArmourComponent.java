package org.colosseumproject.ludus.model;

public enum ArmourComponent {

	SCUTUM(
			"scutum",
			new ArmourResistance(0, 3, 2)),
	PARMA(
			"parma",
			new ArmourResistance(0, 4, 0)),
	CASSISCRISTA(
			"cassis-crista",
			new ArmourResistance(3, 0, 0)),
	GALEA(
			"galea",
			new ArmourResistance(2, 0, 0)),
	MANICAE(
			"manicae",
			new ArmourResistance(0, 3, 0)),
	OCREA(
			"ocrea",
			new ArmourResistance(0, 0, 3)),
	BALTEUS(
			"balteus",
			new ArmourResistance(0, 1, 0));

	private String name;
	private ArmourResistance resistance;

	ArmourComponent(String name, ArmourResistance resistance) {
		this.name = name;
		this.resistance = resistance;
	}

	String getName() {
		return name;
	}

	ArmourResistance getResistance() {
		return resistance;
	}

}
