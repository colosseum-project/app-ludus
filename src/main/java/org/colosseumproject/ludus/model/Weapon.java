package org.colosseumproject.ludus.model;

enum Weapon {

	GLADIUS(
			"gladius", "sword",
			new WeaponDamage(10, 20),
			new WeaponCriticalHit(10, 1.5)),
	DUALGLADIUS(
			"dual-gladius", "sword",
			new WeaponDamage(15, 20),
			new WeaponCriticalHit(12, 1.8)),
	SICA(
			"sica", "dagger",
			new WeaponDamage(5, 20),
			new WeaponCriticalHit(20, 1.4));

	private String name;
	private String type;
	private WeaponDamage damage;
	private WeaponCriticalHit criticalHit;

	Weapon(
			String name,
			String type,
			WeaponDamage damage,
			WeaponCriticalHit criticalHit) {
		this.name = name;
		this.type = type;
		this.damage = damage;
		this.criticalHit = criticalHit;
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

	WeaponCriticalHit getCriticalHit() {
		return criticalHit;
	}

}
