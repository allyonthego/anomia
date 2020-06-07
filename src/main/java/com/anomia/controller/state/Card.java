package com.anomia.controller.state;

import com.anomia.controller.database.CardEntity;
import lombok.Getter;
import lombok.Setter;

public class Card {
    @Getter
    private final int id;
    @Getter
    private CardColour colour;
    @Getter
    @Setter
    private boolean isReveal = false;
    @Getter
    private String word;

    public Card(CardEntity cardEntity) {
        id = cardEntity.getId();
        colour = CardColour.valueOf(cardEntity.getColour());
        word = cardEntity.getWord();
        isReveal = cardEntity.isReveal();
    }
    // testing
    public Card(int id, CardEntity cardEntity) {
        this.id = id;
        colour = CardColour.valueOf(cardEntity.getColour());
        word = cardEntity.getWord();
    }
}
