package org.colosseumproject.ludus.model;

enum Weapon {

	GLADIUS(
			"gladius",
			new WeaponDamage(10, 20, 10)),
	DUALGLADIUS(
			"dual-gladius",
			new WeaponDamage(15, 25, 15)),
	SICA(
			"sica",
			new WeaponDamage(5, 15, 20));

	private String name;
	private WeaponDamage damage;

	Weapon(String name, WeaponDamage damage) {
		this.name = name;
		this.damage = damage;
	}

	String getName() {
		return name;
	}

	WeaponDamage getDamage() {
		return damage;
	}

}
