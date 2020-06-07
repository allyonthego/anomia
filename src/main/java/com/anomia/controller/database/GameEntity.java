package com.anomia.controller.database;

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
}
