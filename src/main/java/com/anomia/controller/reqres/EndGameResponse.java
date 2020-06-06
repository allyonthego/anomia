package com.anomia.controller.reqres;

import com.anomia.controller.data.Game;
import com.anomia.controller.data.Player;
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
