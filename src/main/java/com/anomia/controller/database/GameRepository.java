package com.anomia.controller.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
//    @Query(value="DELETE FROM game WHERE game.id = ?1",nativeQuery=true)
//    void deleteAllById(int id);

    List<GameEntity> deleteById(int id);

    // testing
    GameEntity findGameById(int id);
}