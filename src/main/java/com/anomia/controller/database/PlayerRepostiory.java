package com.anomia.controller.database;

import com.anomia.controller.state.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PlayerRepostiory extends JpaRepository<PlayerEntity, Integer> {
//    @Query(value="DELETE FROM player WHERE player.game_id = ?1",nativeQuery=true)
//    void deleteAllByGameId(int gameId);

    List<PlayerEntity> deleteByGameId(int gameId);

    // testing
    List<PlayerEntity> findAllByGameId(int gameId);
}