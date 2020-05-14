package com.anomia.rest.state;

import lombok.Getter;
import lombok.Setter;

public class Card {
    private static int count = 0;
    @Getter
    private final int id;
    @Getter
    private CardColour colour;
    @Getter
    @Setter
    private boolean isReveal = false;
    @Getter
    private String word;

    public Card(CardColour colour, String word) {
        id = count;
        ++count;
        this.colour = colour;
        this.word = word;
    }
}
