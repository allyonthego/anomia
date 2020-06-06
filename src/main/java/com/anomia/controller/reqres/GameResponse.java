package com.anomia.controller.reqres;

import com.anomia.controller.state.Card;
import com.anomia.controller.state.CardPile;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;


public class GameResponse {
    @Getter
    private int id;
    @Getter
    private Stack<Card> drawPile = new Stack<>();
    @Getter
    private ArrayList<Player> players = new ArrayList<>();;

    public GameResponse(Game game) {
        id = game.getId();
        drawPile = game.getDrawPile();
        players = game.getPlayers();
    }
}
