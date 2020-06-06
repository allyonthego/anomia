package com.anomia.controller;

import com.anomia.controller.reqres.GameResponse;
import com.anomia.controller.reqres.PlayerResponse;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.reqres.StartGameResponse;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AnomiaController {
    private static ArrayList<Game> gameList = new ArrayList<>();

    @PostMapping("/games")
    public StartGameResponse postGame(@RequestBody StartGameRequest req) {
        Game game = new Game(req.getNumPlayers());
        gameList.add(game);
        return new StartGameResponse(game.getId());
    }

    @GetMapping("/games/{gameId}")
    public GameResponse getGame(@PathVariable int gameId) {
        return new GameResponse(gameList.get(gameId));
    }

    @PostMapping("/games/{gameId}/{playerId}/playPile")
    public PlayerResponse postAddPlayPile(@PathVariable int gameId, @PathVariable int playerId) {
        Game game = gameList.get(gameId);
        game.addPlayPile(playerId);
        return new PlayerResponse(game.getPlayerById(playerId));
    }

    // for testing
    public void gameListAdd(Game game) {
        gameList.add(game);
    }
    public void gameListRemove() {
        Game.decrCount();
        gameList.remove(gameList.size() - 1);
    }
}
