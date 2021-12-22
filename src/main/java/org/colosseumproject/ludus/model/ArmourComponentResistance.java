package org.colosseumproject.ludus.model;

class ArmourComponentResistance {

	private Integer headReductionPercentage;
	private Integer upperBodyReductionPercentage;
	private Integer lowerBodyReductionPercentage;

	ArmourComponentResistance(
			Integer headReductionPercentage,
			Integer upperBodyReductionPercentage,
			Integer lowerBodyReductionPercentage) {
		this.headReductionPercentage = headReductionPercentage;
		this.upperBodyReductionPercentage = upperBodyReductionPercentage;
		this.lowerBodyReductionPercentage = lowerBodyReductionPercentage;
	}

	Integer getHeadReductionPercentage() {
		return headReductionPercentage;
	}

	Integer getUpperBodyReductionPercentage() {
		return upperBodyReductionPercentage;
	}

	Integer getLowerBodyReductionPercentage() {
		return lowerBodyReductionPercentage;
	}

}
