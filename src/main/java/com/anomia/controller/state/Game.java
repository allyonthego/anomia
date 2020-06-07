package com.anomia.controller.state;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;


public class Game {
//    private static int count = 0;
    @Getter
    private int id;
    @Getter
//    private Stack<Card> drawPile = new Stack<>();
    private Stack<Card> drawPile;
    @Getter
//    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> players;

//    public Game(int numPlayers) {
//        id = count;
//        ++count;
//        createDrawPile();
//        createPlayers(numPlayers);
//    }

    public Game(int id, ArrayList<Player> players, Stack<Card> drawPile) {
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

//    private void createDrawPile() {
//
//        drawPile = CardPile.createCardPile();
//    }

//    public static void decrCount() {
//        --count;
//    }

    public Player getPlayerById(int playerId) {
        return players.get(playerId);
    }

//    private void createPlayers(int numPlayers) {
//        for (int i = 0; i < numPlayers; ++i) {
//            players.add(new Player());
//        }
//    }
}
