package org.colosseumproject.ludus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.colosseumproject.ludus.model.Gladiator;

@Repository
public interface GladiatorRepository extends JpaRepository<Gladiator, Integer> {

	public Gladiator findByName(String name);

}
