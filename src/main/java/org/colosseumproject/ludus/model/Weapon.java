package org.colosseumproject.ludus.model;

enum Weapon {

	GLADIUS(
			"Gladius", "Short sword",
			new WeaponDamage(10, 20, 10)),
	DUALGLADIUS(
			"Dual gladius", "Dual short swords",
			new WeaponDamage(15, 25, 15)),
	SICA(
			"Sica", "Curved large dagger",
			new WeaponDamage(5, 15, 20));

	private String name;
	private String description;
	private WeaponDamage damage;

	Weapon(String name, String description, WeaponDamage damage) {
		this.name = name;
		this.description = description;
		this.damage = damage;
	}

	String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	WeaponDamage getDamage() {
		return damage;
	}

}
