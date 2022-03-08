package org.colosseumproject.ludus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.colosseumproject.ludus.model.DuelResult;

@Repository
public interface DuelResultRepository extends JpaRepository<DuelResult, Integer> {

}
