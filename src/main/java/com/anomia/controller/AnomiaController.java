package com.anomia.controller;

import com.anomia.controller.reqres.AddWinRequest;
import com.anomia.controller.reqres.EndGameResponse;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.reqres.StartGameResponse;
import com.anomia.controller.service.GameService;
import com.anomia.controller.state.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AnomiaController {
    @Autowired
    GameService gameService;
    private static HashMap<Integer, Game> gameList = new HashMap<Integer, Game>();

    @DeleteMapping("/games/{gameId}")
    public EndGameResponse deleteGame(@PathVariable int gameId) {
        EndGameResponse endGameResponse = new EndGameResponse(gameList.get(gameId));
        gameService.endGame(gameId);
        gameList.remove(gameId);
        return endGameResponse;
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

    @PostMapping("/games")
    public StartGameResponse postGame(@RequestBody StartGameRequest req) {
        Game game = gameService.startGame(req.getNumPlayers());
//        Game game = new Game(req.getNumPlayers());
        gameList.put(game.getId(),game);
        return new StartGameResponse(game.getId());
    }

    // for testing
    public void gameListAdd(Game game) {
        gameList.put(game.getId(),game);
    }
    public int gameListCount() { return gameList.size(); }
//    public void gameListRemove() {
//        gameListRemove(gameList.size() - 1);
//    }
    public void gameListRemove(int gameId) {
//        Game.decrCount();
        gameList.remove(gameId);
    }
}
