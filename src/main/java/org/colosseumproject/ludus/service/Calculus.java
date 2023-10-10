package org.colosseumproject.ludus.service;

// TODO To be removed from this application
public class Calculus {
	public static Integer ratio(Integer antecedent, Integer consequent) {
		Integer ratioMultiplier = 100;
		Double a, c;
		if (antecedent == 0) {
			a = 1.00;
		} else {
			a = Double.valueOf(antecedent);
		}
		if (consequent == 0) {
			c = 1.00;
		} else {
			c = Double.valueOf(consequent);
		}
		return (int) (a / c * ratioMultiplier);
	}
}
