package org.colosseumproject.ludus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import org.colosseumproject.ludus.view.GladiatorViews;

@Entity
@Table(name = "gladiators")
public class Gladiator extends BaseEntity {

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private GladiatorType type;

	public static Integer health = 100;

	public Gladiator() {
	}

	public Gladiator(String name, GladiatorType type) {
		this.name = name;
		this.type = type;
	}

	@JsonView(GladiatorViews.Summary.class)
	public Integer getId() {
		return super.getId();
	}

	@JsonView(GladiatorViews.Summary.class)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(GladiatorViews.Summary.class)
	public GladiatorType getType() {
		return this.type;
	}

	public void setType(GladiatorType type) {
		this.type = type;
	}

	@JsonView(GladiatorViews.Ability.class)
	public Map<String, Object> getAbility() {
		Map<String, Object> ability = new HashMap<String, Object>();
		ability.put("health", health);

		Map<String, Integer> damage = new HashMap<String, Integer>();
		damage.put("minimum", type.getWeapon().getDamage().getMinimum());
		damage.put("maximum", type.getWeapon().getDamage().getMaximum());
		damage.put("criticalPercentage", type.getWeapon().getDamage().getCriticalPercentage());
		ability.put("damage", damage);

		Map<String, Integer> resistance = new HashMap<String, Integer>();
		Integer resHead, resUpperBody, resLowerBody;
		resHead = resUpperBody = resLowerBody = 0;
		for (ArmourComponent armourComponent : type.getArmourSet()) {
			resHead += armourComponent.getResistance().getHead();
			resUpperBody += armourComponent.getResistance().getUpperBody();
			resLowerBody += armourComponent.getResistance().getLowerBody();
		}
		resistance.put("head", resHead);
		resistance.put("upperBody", resUpperBody);
		resistance.put("lowerBody", resLowerBody);
		ability.put("resistance", resistance);

		return ability;
	}

	@JsonView(GladiatorViews.Equipment.class)
	public Map<String, Object> getEquipment() {
		Map<String, Object> equipment = new HashMap<String, Object>();

		Map<String, Object> weapon = new HashMap<String, Object>();
		weapon.put("name", type.getWeapon().getName());
		weapon.put("type", type.getWeapon().getType());

		Map<String, Integer> weaponDamage = new HashMap<String, Integer>();
		weaponDamage.put("minimum", type.getWeapon().getDamage().getMinimum());
		weaponDamage.put("maximum", type.getWeapon().getDamage().getMaximum());
		weaponDamage.put("criticalPercentage", type.getWeapon().getDamage().getCriticalPercentage());
		weapon.put("damage", weaponDamage);

		equipment.put("weapon", weapon);

		List<Object> armour = new ArrayList<Object>();

		for (ArmourComponent armourComponent : type.getArmourSet()) {
			Map<String, Object> component = new HashMap<String, Object>();
			component.put("name", armourComponent.getName());
			component.put("type", armourComponent.getType());

			Map<String, Integer> resistance = new HashMap<String, Integer>();
			resistance.put("head", armourComponent.getResistance().getHead());
			resistance.put("upperBody", armourComponent.getResistance().getUpperBody());
			resistance.put("lowerBody", armourComponent.getResistance().getLowerBody());
			component.put("resistance", resistance);

			armour.add(component);
		}

		equipment.put("armour", armour);

		return equipment;
	}

}
