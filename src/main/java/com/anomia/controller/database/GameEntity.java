package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity {
    @Getter
    @Id
    @GeneratedValue
    private int id;
    public GameEntity() {}
}
