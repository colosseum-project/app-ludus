package org.colosseumproject.ludus.model;

class ArmourResistance {

	private Integer head;
	private Integer upperBody;
	private Integer lowerBody;

	ArmourResistance(Integer head, Integer upperBody, Integer lowerBody) {
		this.head = head;
		this.upperBody = upperBody;
		this.lowerBody = lowerBody;
	}

	Integer getHead() {
		return head;
	}

	Integer getUpperBody() {
		return upperBody;
	}

	Integer getLowerBody() {
		return lowerBody;
	}

}
