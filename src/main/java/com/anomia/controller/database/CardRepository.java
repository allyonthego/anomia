package com.anomia.controller.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
//    @Query(value="DELETE FROM card WHERE card.game_id = ?1",nativeQuery=true)
//    void deleteAllByGameId(int gameId);
    List<CardEntity> deleteByGameId(int gameId);

    // testing
    List<CardEntity> findAllByGameId(int gameId);
}