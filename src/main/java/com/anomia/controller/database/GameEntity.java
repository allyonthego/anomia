package com.anomia.controller.database;

import com.anomia.controller.state.Game;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "game")
public class GameEntity {
    @Getter
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    public GameEntity() {}
    public GameEntity(Game game) { id = game.getId(); }
}
