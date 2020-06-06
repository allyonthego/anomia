package com.anomia.controller.state;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;


public class Game {
    private static int count = 0;
    @Getter
    private int id;
    @Getter
    private Stack<Card> drawPile = new Stack<>();
    @Getter
    private ArrayList<Player> players = new ArrayList<>();;

    public Game(int numPlayers) {
        id = count;
        ++count;
        createDrawPile();
        createPlayers(numPlayers);
    }

    public Player getPlayerById(int playerId) {
        return players.get(playerId);
    }

    public void addPlayPile(int playerId) {
        getPlayerById(playerId).addPlayPile(drawPile.pop());
    }

    public static void decrCount() {
        --count;
    }

    private void createDrawPile() {
        drawPile = CardPile.createPile();
    }

    private void createPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; ++i) {
            players.add(new Player());
        }
    }
}
