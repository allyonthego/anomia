package com.anomia.controller.database;

import com.anomia.controller.state.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    CardEntity findCardById(int id);
}