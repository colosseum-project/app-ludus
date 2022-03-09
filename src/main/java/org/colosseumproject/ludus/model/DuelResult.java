package org.colosseumproject.ludus.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

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

	@JsonView(DuelResultViews.Default.class)
	public Gladiator getFirstGladiator() {
		// json.firstGladiator
		return firstGladiator;
	}

	@JsonView(DuelResultViews.Default.class)
	public Gladiator getSecondGladiator() {
		// json.secondGladiator
		return secondGladiator;
	}

	@JsonView(DuelResultViews.Default.class)
	public Gladiator getWinner() {
		// json.secondGladiator
		return winner;
	}

	@JsonView(DuelResultViews.Default.class)
	public List<String> getCombatLogs() {
		// json.combatLogs[]
		return combatLogs;
	}

	@JsonView(DuelResultViews.Default.class)
	public Timestamp getTimestamp() {
		// json.timestamp
		return timestamp;
	}

}
