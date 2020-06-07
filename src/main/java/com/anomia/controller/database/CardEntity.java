package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CardEntity {
    @Getter
    @Id
    @GeneratedValue
    private int id;
    @Getter
    private String colour;
    @Getter
    private String word;
    @Getter
    private int gameId;
    @Getter
    private int playerId;
    @Getter
    private boolean winPile;
    @Getter
    private boolean playPile;
    @Getter
    private boolean isReveal;

    public CardEntity() {}

    // testing only
//    public CardEntity(String colour, String word) {
//        this.colour = colour;
//        this.word = word;
//    }
    public CardEntity(int id, String colour, String word) {
        this.id = id;
        this.colour = colour;
        this.word = word;
    }
}
