package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "card")
public class CardEntity {
    @Getter
    @Id
    @GeneratedValue(strategy=IDENTITY)
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
    private boolean isDrawPile = true;
    @Getter
    private boolean isWinPile = false;
    @Getter
    private boolean isPlayPile = false;
    @Getter
    private boolean isReveal = false;

    public CardEntity() {}

    public CardEntity(int gameId, String colour, String word) {
        this.gameId = gameId;
        this.colour = colour;
        this.word = word;
    }
}
