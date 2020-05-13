package com.anomia.rest;

import com.anomia.rest.request.StartGameRequest;
import com.anomia.rest.state.Game;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnomiaController {
    private static final Game game = new Game();

    @PostMapping("/start")
    public int postStartGame(@RequestBody StartGameRequest req) {
        System.out.println(req);
        return game.startGame(req.getNumPlayers());
    }
}
