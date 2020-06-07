package com.anomia.controller.service;

import com.anomia.controller.database.*;
import com.anomia.controller.state.Card;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static com.anomia.controller.state.CardEntityPile.createCardEntityPile;

@Transactional
@Service
public class GameService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepostiory playerRepostiory;

    public Game startGame(int numPlayers) {
        GameEntity gameEntity = gameRepository.save(new GameEntity());
        int gameId = gameEntity.getId();

        HashMap<Integer, Player> players = new HashMap<>();
        for (int i = 0; i < numPlayers; ++i) {
            int playerId = playerRepostiory.save(new PlayerEntity(gameId)).getId();
            players.put(playerId, new Player(playerId));
        }

        List<CardEntity> cardEntities = cardRepository.saveAll(createCardEntityPile(gameId));
        Stack<Card> drawPile = new Stack<>();
        for (CardEntity cardEntity: cardEntities) {
            drawPile.push(new Card(cardEntity));
        }

        Game game = new Game(gameId, players, drawPile);
        return game;
    }

    public void endGame(int gameId) {
        cardRepository.deleteByGameId(gameId);
        playerRepostiory.deleteByGameId(gameId);
        gameRepository.deleteById(gameId);
    }
}
