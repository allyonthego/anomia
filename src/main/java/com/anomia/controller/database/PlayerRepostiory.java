package com.anomia.controller.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepostiory extends JpaRepository<PlayerEntity, Integer> {

    List<PlayerEntity> deleteByGameId(int gameId);

    // testing
    List<PlayerEntity> findAllByGameId(int gameId);
}