package org.colosseumproject.ludus.model;

public enum ArmourComponent {

	SCUTUM(
			"Scutum", "Rectangular shield",
			new ArmourResistance(0, 3, 2)),
	PARMA(
			"Parma", "Round shield",
			new ArmourResistance(0, 4, 0)),
	CASSISCRISTA(
			"Cassis crista", "Heavy bronze helmet",
			new ArmourResistance(3, 0, 0)),
	GALEA(
			"Galea", "Plumed helmet",
			new ArmourResistance(2, 0, 0)),
	MANICAE(
			"Manicae", "Arm guard",
			new ArmourResistance(0, 3, 0)),
	OCREA(
			"Ocrea", "Greaves",
			new ArmourResistance(0, 0, 3)),
	BALTEUS(
			"Balteus", "Belt worn over one shoulder",
			new ArmourResistance(0, 1, 0));

	private String name;
	private String description;
	private ArmourResistance resistance;

	ArmourComponent(String name, String description, ArmourResistance resistance) {
		this.name = name;
		this.description = description;
		this.resistance = resistance;
	}

	String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	ArmourResistance getResistance() {
		return resistance;
	}

}
