package com.anomia.controller.reqres;

import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import lombok.Getter;

import java.util.HashMap;

public class EndGameResponse {
    @Getter
    private HashMap<Integer, Integer> cardCounts;

    public EndGameResponse() {}
    public EndGameResponse(Game game) {
        cardCounts = new HashMap<>();
        for (Player plyr: game.getPlayers()) {
            cardCounts.put(plyr.getId(), plyr.getWinPile().size());
        }
    }
}
