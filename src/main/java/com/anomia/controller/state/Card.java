package com.anomia.controller.state;

import com.anomia.controller.database.CardEntity;
import lombok.Getter;
import lombok.Setter;

enum CardColour {BLUE, BROWN, GREEN, ORANGE, PINK, PURPLE, RED, YELLOW}

public class Card {
//    private static int count = 0;
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
    }
}
