package com.anomia.controller.state;

import lombok.Getter;

import java.util.HashMap;
import java.util.Stack;


public class Game {
    @Getter
    private int id;
    @Getter
    private Stack<Card> drawPile;
    @Getter
    private HashMap<Integer, Player> players;

    public Game(int id, HashMap<Integer, Player> players, Stack<Card> drawPile) {
        this.id = id;
        this.drawPile = drawPile;
        this.players = players;
    }

    public void addPlayPile(int playerId) {
        getPlayerById(playerId).addPlayPile(drawPile.pop());
    }

    public void addWinPile(int winId, int loseId) {
        getPlayerById(winId).addWinPile(getPlayerById(loseId).takePlayPile());
    }

    public Player getPlayerById(int playerId) {
        return players.get(playerId);
    }

}
