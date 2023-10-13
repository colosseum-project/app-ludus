package org.colosseumproject.ludus.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import org.colosseumproject.ludus.view.DuelResultViews;

@Entity
@Table(name = "duel_results")
public class DuelResult extends BaseEntity {

	// TODO Replace FetchType.EAGER by FetchType.LAZY to reduce performance impact

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "gladiator_1_id", nullable = false)
	private Gladiator firstGladiator;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "gladiator_2_id", nullable = false)
	private Gladiator secondGladiator;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "winner_id", nullable = false)
	private Gladiator winner;

	@Column(name = "combat_log")
	@OrderColumn
	@ElementCollection(fetch = FetchType.EAGER)
	List<String> combatLogs;

	private Timestamp timestamp;

	public DuelResult() {
	}

	public DuelResult(
			Gladiator firstGladiator,
			Gladiator secondGladiator,
			Gladiator winner,
			List<String> combatLogs) {
		super();
		this.firstGladiator = firstGladiator;
		this.secondGladiator = secondGladiator;
		this.winner = winner;
		this.combatLogs = combatLogs;
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	@JsonView(DuelResultViews.IdOnly.class)
	public Integer getId() {
		// json.id
		return super.getId();
	}

	@JsonView(DuelResultViews.IdOnly.class)
	public Gladiator getFirstGladiator() {
		// json.firstGladiator
		return firstGladiator;
	}

	public void setFirstGladiator(Gladiator gladiator) {
		firstGladiator = gladiator;
	}

	@JsonView(DuelResultViews.IdOnly.class)
	public Gladiator getSecondGladiator() {
		// json.secondGladiator
		return secondGladiator;
	}

	public void setSecondGladiator(Gladiator gladiator) {
		secondGladiator = gladiator;
	}

	@JsonView(DuelResultViews.IdOnly.class)
	public Gladiator getWinner() {
		// json.secondGladiator
		return winner;
	}

	public void setWinner(Gladiator gladiator) {
		winner = gladiator;
	}

	@JsonView(DuelResultViews.Detailed.class)
	public List<String> getCombatLogs() {
		// json.combatLogs[]
		return combatLogs;
	}

	@JsonView(DuelResultViews.Summary.class)
	public Timestamp getTimestamp() {
		// json.timestamp
		return timestamp;
	}

	public void updateTimestamp() {
		timestamp = new Timestamp(System.currentTimeMillis());
	}

}
