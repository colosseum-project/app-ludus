package org.colosseumproject.ludus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;

import org.colosseumproject.ludus.view.GladiatorViews;

@Entity
@Table(name = "gladiators")
public class Gladiator extends BaseEntity {

	@Column(name = "name", unique = true, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String name;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private GladiatorType type;

	public static Integer hitPoint = 100;
	public static Integer baseParadeChancePercentage = 0;
	public static Integer baseEvasionChancePercentage = 15;

	public Gladiator() {
	}

	public Gladiator(
			String name,
			GladiatorType type) {
		this.name = name;
		this.type = type;
	}

	@JsonView(GladiatorViews.IdOnly.class)
	public Integer getId() {
		// json.id
		return super.getId();
	}

	@JsonView(GladiatorViews.Summary.class)
	public String getName() {
		// json.name
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(GladiatorViews.Summary.class)
	public GladiatorType getType() {
		// json.type
		return type;
	}

	public void setType(GladiatorType type) {
		this.type = type;
	}

	@JsonView(GladiatorViews.Attributes.class)
	public Integer getHitPoint() {
		// json.hitPoint
		return hitPoint;
	}

	@JsonView(GladiatorViews.Attributes.class)
	public Map<String, Object> getAttack() {
		// json.attack
		Map<String, Object> attack = new HashMap<String, Object>();

		// json.attack.damage
		Map<String, Object> damage = new HashMap<String, Object>();
		damage.put("minimum", type.getWeapon().getDamage().getMinimum());
		damage.put("maximum", type.getWeapon().getDamage().getMaximum());
		attack.put("damage", damage);

		// json.attack.criticalHit
		Map<String, Object> criticalHit = new HashMap<String, Object>();
		criticalHit.put("chancePercentage", type.getWeapon().getCriticalHit().getChancePercentage());
		criticalHit.put("multiplier", type.getWeapon().getCriticalHit().getMultiplier());
		attack.put("criticalHit", criticalHit);

		return attack;
	}

	@JsonView(GladiatorViews.Attributes.class)
	public Map<String, Object> getDefense() {
		// json.defense
		Map<String, Object> defense = new HashMap<String, Object>();

		// json.defense.resistance
		Map<String, Object> resistance = new HashMap<String, Object>();
		Integer resistanceHeadReductionPercentage = 0,
				resistanceUpperBodyReductionPercentage = 0,
				resistanceLowerBodyReductionPercentage = 0;
		for (ArmourComponent armourComponent : type.getArmourSet()) {
			resistanceHeadReductionPercentage += armourComponent.getResistance().getHeadReductionPercentage();
			resistanceUpperBodyReductionPercentage += armourComponent.getResistance().getUpperBodyReductionPercentage();
			resistanceLowerBodyReductionPercentage += armourComponent.getResistance().getLowerBodyReductionPercentage();
		}
		resistance.put("headReductionPercentage", resistanceHeadReductionPercentage);
		resistance.put("upperBodyReductionPercentage", resistanceUpperBodyReductionPercentage);
		resistance.put("lowerBodyReductionPercentage", resistanceLowerBodyReductionPercentage);
		defense.put("resistance", resistance);

		// json.defense.paradeChancePercentage
		Integer paradeChancePercentage = baseParadeChancePercentage;
		for (ArmourComponent armourComponent : type.getArmourSet()) {
			paradeChancePercentage += armourComponent.getParadeChancePercentage();
		}
		defense.put("paradeChancePercentage", paradeChancePercentage);

		// json.defense.evasionChancePercentage
		Integer evasionChancePercentage = baseEvasionChancePercentage;
		for (ArmourComponent armourComponent : type.getArmourSet()) {
			evasionChancePercentage -= armourComponent.getEvasionPenaltyPercentage();
		}
		defense.put("evasionChancePercentage", evasionChancePercentage);

		return defense;
	}

	@JsonView(GladiatorViews.Equipment.class)
	public Map<String, Object> getEquipment() {
		// json.equipment
		Map<String, Object> equipment = new HashMap<String, Object>();

		// json.equipment.weapon
		Map<String, Object> weapon = new HashMap<String, Object>();
		weapon.put("name", type.getWeapon().getName());
		weapon.put("type", type.getWeapon().getType());

		// json.equipment.weapon.damage
		Map<String, Object> weaponDamage = new HashMap<String, Object>();
		weaponDamage.put("minimum", type.getWeapon().getDamage().getMinimum());
		weaponDamage.put("maximum", type.getWeapon().getDamage().getMaximum());
		weapon.put("damage", weaponDamage);

		// json.equipment.weapon.criticalHit
		Map<String, Object> weaponCriticalHit = new HashMap<String, Object>();
		weaponCriticalHit.put("chancePercentage", type.getWeapon().getCriticalHit().getChancePercentage());
		weaponCriticalHit.put("multiplier", type.getWeapon().getCriticalHit().getMultiplier());
		weapon.put("criticalHit", weaponCriticalHit);

		equipment.put("weapon", weapon);

		// json.equipment.armour[]
		List<Object> armour = new ArrayList<Object>();
		for (ArmourComponent armourComponent : type.getArmourSet()) {
			// json.equipment.armour[].component
			Map<String, Object> component = new HashMap<String, Object>();
			component.put("name", armourComponent.getName());
			component.put("type", armourComponent.getType());
			component.put("paradeChancePercentage", armourComponent.getParadeChancePercentage());
			component.put("evasionPenaltyPercentage", armourComponent.getEvasionPenaltyPercentage());

			// json.equipment.armour[].component.resistance
			Map<String, Object> componentResistance = new HashMap<String, Object>();
			componentResistance.put("headReductionPercentage",
					armourComponent.getResistance().getHeadReductionPercentage());
			componentResistance.put("upperBodyReductionPercentage",
					armourComponent.getResistance().getUpperBodyReductionPercentage());
			componentResistance.put("lowerBodyReductionPercentage",
					armourComponent.getResistance().getLowerBodyReductionPercentage());
			component.put("resistance", componentResistance);

			armour.add(component);
		}

		equipment.put("armour", armour);

		return equipment;
	}

}
