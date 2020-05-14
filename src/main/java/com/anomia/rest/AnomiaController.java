package com.anomia.rest;

import com.anomia.rest.json.StartGameRequest;
import com.anomia.rest.json.StartGameResponse;
import com.anomia.rest.state.Game;
import com.anomia.rest.state.Player;
import lombok.Getter;
import lombok.Setter;
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
    public Player postAddPlayPile(@PathVariable int gameId, @PathVariable int playerId) {
        Game game = gameList.get(gameId);
        game.addPlayPile(playerId);
        return game.getPlayerById(playerId);
    }

    public void gameListAdd(Game game) {
        gameList.add(game);
    }

    public void gameListRemove() {
        Game.decrCount();
        gameList.remove(gameList.size() - 1);
    }
}
