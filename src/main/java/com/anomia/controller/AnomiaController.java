package com.anomia.controller;

import com.anomia.controller.database.CardEntity;
import com.anomia.controller.database.CardRepository;
import com.anomia.controller.state.Game;
import com.anomia.controller.reqres.AddWinRequest;
import com.anomia.controller.reqres.EndGameResponse;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.reqres.StartGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AnomiaController {
    @Autowired
    CardRepository cardRepository;
    private static ArrayList<Game> gameList = new ArrayList<>();

    @DeleteMapping("/games/{gameId}")
    public EndGameResponse deleteGame(@PathVariable int gameId) {
        Game game = gameList.get(gameId);
        EndGameResponse endGameResponse = new EndGameResponse(game);
        gameListRemove(game.getId());
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
        Game game = new Game(req.getNumPlayers());
        gameList.add(game);
        cardRepository.save(new CardEntity(0, "BLUE", "Hello"));
        System.out.println(cardRepository.findCardById(0));
        return new StartGameResponse(game.getId());
    }

    public void gameListAdd(Game game) {
        gameList.add(game);
    }
    public int gameListCount() { return gameList.size(); }
    public void gameListRemove() {
        gameListRemove(gameList.size() - 1);
    }
    public void gameListRemove(int gameId) {
        Game.decrCount();
        gameList.remove(gameId);
    }
}
