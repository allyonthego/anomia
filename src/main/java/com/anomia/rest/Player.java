package com.anomia.rest;

import lombok.Getter;

import java.util.Set;
import java.util.Stack;

public class Player {
    private static int count = 0;
    @Getter
    private final int id;
    private Stack<Card> playPile;
    private Set<Card> winPile;

    public Player() {
        id = count;
        ++count;
    }
}
