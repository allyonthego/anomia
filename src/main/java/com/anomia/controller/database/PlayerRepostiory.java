package com.anomia.controller.database;

import com.anomia.controller.state.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlayerRepostiory extends JpaRepository<PlayerEntity, Integer> {
}