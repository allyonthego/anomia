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

    @DeleteMapping("/games/{gameId}/save")
    public EndGameResponse deleteSaveGame(@PathVariable int gameId) {
        EndGameResponse endGameResponse = new EndGameResponse(gameList.get(gameId));
        gameService.endSaveGame(gameList.get(gameId));
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

    // requirement: req.getLoseId() cannot have empty play pile
    @PostMapping("/games/{gameId}/{playerId}/winPile")
    public void postAddWinPile(@PathVariable int gameId, @PathVariable int playerId,
                               @RequestBody AddWinRequest req) {
        Game game = gameList.get(gameId);
        game.addWinPile(playerId, req.getLoseId());
    }

    @PostMapping("/games")
    public StartGameResponse postGame(@RequestBody StartGameRequest req) {
        Game game = gameService.startGame(req.getNumPlayers());
        gameList.put(game.getId(),game);
        return new StartGameResponse(game.getId());
    }

    // requirement: cannot postSaveGame in the middle of a game
    @PostMapping("/games/{gameId}/save")
    public StartGameResponse postSaveGame(@PathVariable int gameId) {
        Game game = gameService.startSaveGame(gameId);
        gameList.put(game.getId(),game);
        return new StartGameResponse(game.getId());
    }

    // testing
    public void gameListAdd(Game game) {
        gameList.put(game.getId(),game);
    }
    public int gameListCount() { return gameList.size(); }
    public void gameListRemove(int gameId) { gameList.remove(gameId); }
}
