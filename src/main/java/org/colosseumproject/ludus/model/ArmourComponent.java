package org.colosseumproject.ludus.model;

public enum ArmourComponent {

	SCUTUM(
			"scutum", "rectangular_shield",
			new ArmourResistance(0, 3, 2)),
	PARMA(
			"parma", "round_shield",
			new ArmourResistance(0, 4, 0)),
	CASSISCRISTA(
			"cassis crista", "heavy_helmet",
			new ArmourResistance(3, 0, 0)),
	GALEA(
			"galea", "plumed_helmet",
			new ArmourResistance(2, 0, 0)),
	MANICAE(
			"manicae", "arm_guard",
			new ArmourResistance(0, 3, 0)),
	OCREA(
			"ocrea", "greave",
			new ArmourResistance(0, 0, 3)),
	BALTEUS(
			"balteus", "belt",
			new ArmourResistance(0, 1, 0));

	private String name;
	private String type;
	private ArmourResistance resistance;

	ArmourComponent(String name, String type, ArmourResistance resistance) {
		this.name = name;
		this.type = type;
		this.resistance = resistance;
	}

	String getName() {
		return name;
	}

	String getType() {
		return type;
	}

	ArmourResistance getResistance() {
		return resistance;
	}

}
