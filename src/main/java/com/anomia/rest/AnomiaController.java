package com.anomia.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnomiaController {
    private static final Game game = new Game();

    @GetMapping("/start")
    public int getStartGameId() {
        int numPlayers = 4;
        return game.startGame(numPlayers);
    }
}
