package com.anomia.controller.database;

import com.anomia.controller.state.Card;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

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
    // drawPile = 1, playPile=2, winPile=3
    @Getter
    private int whichPile = 1;
    @Getter
    boolean isReveal = false;

    public CardEntity() {}

    public CardEntity(int gameId, String colour, String word) {
        this.gameId = gameId;
        this.colour = colour;
        this.word = word;
    }


    public CardEntity(int gameId, int playerId, Card card, int whichPile) {
        this.id = card.getId();
        this.colour = card.getColour().toString();
        this.word = card.getWord();
        this.gameId = gameId;
        this.playerId = playerId;
        this.whichPile = whichPile;
        this.isReveal = card.isReveal();
    }
}
