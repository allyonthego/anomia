package com.anomia.controller;

import com.anomia.controller.data.Player;
import com.anomia.controller.reqres.*;
import com.anomia.controller.data.Game;
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
    public Game getGame(@PathVariable int gameId) {
        return gameList.get(gameId);
    }

    @PostMapping("/games/{gameId}/{playerId}/playPile")
    public void postAddPlayPile(@PathVariable int gameId, @PathVariable int playerId) {
        Game game = gameList.get(gameId);
        game.addPlayPile(playerId);
    }

    // req.getLoseId() cannot have empty play pile
    @PostMapping("/games/{gameId}/{playerId}/winPile")
    public void postAddWinPile(@PathVariable int gameId, @PathVariable int playerId,
        @RequestBody AddWinRequest req) {
        Game game = gameList.get(gameId);
        game.addWinPile(playerId, req.getLoseId());
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
