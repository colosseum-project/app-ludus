package org.colosseumproject.ludus.model;

enum Weapon {

	GLADIUS(
			"gladius", "short_sword",
			new WeaponDamage(10, 20, 10)),
	DUALGLADIUS(
			"dual gladius", "dual_short_swords",
			new WeaponDamage(15, 25, 15)),
	SICA(
			"sica", "curved_dagger",
			new WeaponDamage(5, 15, 20));

	private String name;
	private String type;
	private WeaponDamage damage;

	Weapon(String name, String type, WeaponDamage damage) {
		this.name = name;
		this.type = type;
		this.damage = damage;
	}

	String getName() {
		return name;
	}

	String getType() {
		return type;
	}

	WeaponDamage getDamage() {
		return damage;
	}

}
