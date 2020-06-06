package com.anomia.controller.reqres;

import com.anomia.controller.state.Card;
import com.anomia.controller.state.Player;
import lombok.Getter;

import java.util.Stack;

public class PlayerResponse {
    @Getter
    private final int id;
    @Getter
    private Stack<Card> playPile = new Stack<>();
    @Getter
    private Stack<Card> winPile = new Stack<>();

    public PlayerResponse(Player player) {
        id = player.getId();
        playPile = player.getPlayPile();
        winPile = player.getWinPile();
    }
}
