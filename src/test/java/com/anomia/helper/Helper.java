package com.anomia.helper;

import com.anomia.controller.database.CardEntity;
import com.anomia.controller.state.Card;
import com.anomia.controller.state.CardPile;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Helper {
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Game createEmptyGame() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(0));
        players.add(new Player(1));
        Stack<CardEntity> cardEntities = CardPile.createCardEntityPile();
        Stack<Card> drawPile = new Stack<>();
        for (CardEntity cardEntity: cardEntities) {
            drawPile.push(new Card(cardEntity));
        }
        return new Game(0, players,drawPile);
    }
}
