package com.anomia.controller.state;

import lombok.Getter;

import java.util.Stack;

public class Player {
    private static int count = 0;
    @Getter
    private final int id;
    @Getter
    private Stack<Card> playPile = new Stack<>();
    @Getter
    private Stack<Card> winPile = new Stack<>();

    public Player() {
        id = count;
        ++count;
    }

    public void addPlayPile(Card card) {
        if (playPile.size() != 0) {
            playPile.peek().setReveal(false);
        }
        card.setReveal(true);
        playPile.push(card);
    }
}
