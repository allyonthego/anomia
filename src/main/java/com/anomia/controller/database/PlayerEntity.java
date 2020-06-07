package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Getter
    @GeneratedValue
    @Id
    private int id;
    public PlayerEntity() {}
}
