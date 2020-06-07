package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

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

}
