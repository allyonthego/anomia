package com.anomia.rest.state;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;


public class Game {
    private static int count = 0;
    @Getter
    private final int id;
    @Getter
    private Stack<Card> drawPile;
    @Getter
    private ArrayList<Player> players;

    public Game() {
        id = count;
        ++count;
    }

    public int startGame(int numPlayers) {
        createDrawPile();
        createPlayers(numPlayers);
        return id;
    }

    private void createDrawPile() {
        drawPile = CardPile.createPile();
    }
    private void createPlayers(int numPlayers) {
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; ++i) {
            players.add(new Player());
        }
    }
}
