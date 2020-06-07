package com.anomia.controller.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    List<CardEntity> findAllByGameIdAndWhichPileAndIsReveal(int gameId,int whichPile,boolean isReveal);
    List<CardEntity> deleteByGameId(int gameId);

    // testing
    List<CardEntity> findAllByGameId(int gameId);

}