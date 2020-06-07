package com.anomia.controller.database;

import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Getter
    @GeneratedValue(strategy=IDENTITY)
    @Id
    private int id;
    @Getter
    private int gameId;

    public PlayerEntity() {}
    public PlayerEntity(int gameId) {
        this.gameId = gameId;
    }
    public PlayerEntity(Game game, Player player) {
        id = player.getId();
        gameId = game.getId();
    }

}
