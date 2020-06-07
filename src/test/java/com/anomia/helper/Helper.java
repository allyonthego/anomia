package com.anomia.helper;

import com.anomia.controller.database.CardEntity;
import com.anomia.controller.state.Card;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Stack;

import static com.anomia.controller.state.CardEntityPile.*;

public class Helper {
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Game createEmptyGame() {
        HashMap<Integer, Player> players = new HashMap<>();
        players.put(1, new Player(1));
        players.put(2, new Player(2));
        Game game = new Game(1, players,createCardPile());
        return game;
    }
}
