package org.colosseumproject.ludus.model;

enum ArmourComponent {

	SCUTUM(
			"scutum", "shield",
			new ArmourComponentResistance(5, 10, 10),
			15, 5),
	PARMA(
			"parma", "shield",
			new ArmourComponentResistance(5, 10, 0),
			12, 2),
	CASSISCRISTA(
			"cassis-crista", "helmet",
			new ArmourComponentResistance(15, 0, 0),
			0, 5),
	GALEA(
			"galea", "helmet",
			new ArmourComponentResistance(10, 0, 0),
			0, 2),
	MANICAE(
			"manicae", "bracers",
			new ArmourComponentResistance(0, 5, 0),
			1, 1),
	OCREA(
			"ocrea", "greaves",
			new ArmourComponentResistance(0, 0, 5),
			1, 1),
	BALTEUS(
			"balteus", "belt",
			new ArmourComponentResistance(0, 5, 0),
			0, 0);

	private String name;
	private String type;
	private ArmourComponentResistance resistance;
	private Integer paradeChancePercentage;
	private Integer evasionPenaltyPercentage;

	ArmourComponent(
			String name,
			String type,
			ArmourComponentResistance resistance,
			Integer paradeChancePercentage,
			Integer evasionPenaltyPercentage) {
		this.name = name;
		this.type = type;
		this.resistance = resistance;
		this.paradeChancePercentage = paradeChancePercentage;
		this.evasionPenaltyPercentage = evasionPenaltyPercentage;
	}

	String getName() {
		return name;
	}

	String getType() {
		return type;
	}

	ArmourComponentResistance getResistance() {
		return resistance;
	}

	Integer getParadeChancePercentage() {
		return paradeChancePercentage;
	}

	Integer getEvasionPenaltyPercentage() {
		return evasionPenaltyPercentage;
	}

}
