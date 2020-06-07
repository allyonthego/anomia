package com.anomia.controller.reqres;

import com.anomia.controller.state.Card;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;


public class GetGameResponse {
    @Getter
    private int id;
    @Getter
    private Stack<Card> drawPile;
    @Getter
    private ArrayList<Player> players;

    public GetGameResponse() {}

    public GetGameResponse(Game game) {
        id = game.getId();
        drawPile = game.getDrawPile();
        players = new ArrayList<>();
        for (Player plyr: game.getPlayers().values()) {
            players.add(plyr);
        }
    }
}
