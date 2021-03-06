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

	@JsonView(DuelResultViews.Summary.class)
	public Integer getId() {
		// json.id
		return super.getId();
	}

	@JsonView(DuelResultViews.Summary.class)
	public Gladiator getFirstGladiator() {
		// json.firstGladiator
		return firstGladiator;
	}

	public void setFirstGladiator(Gladiator gladiator) {
		firstGladiator = gladiator;
	}

	@JsonView(DuelResultViews.Summary.class)
	public Gladiator getSecondGladiator() {
		// json.secondGladiator
		return secondGladiator;
	}

	public void setSecondGladiator(Gladiator gladiator) {
		secondGladiator = gladiator;
	}

	@JsonView(DuelResultViews.Summary.class)
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
