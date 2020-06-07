package com.anomia.controller.service;

import com.anomia.controller.database.*;
import com.anomia.controller.state.Card;
import com.anomia.controller.state.CardPile;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; ++i) {
            int playerId = playerRepostiory.save(new PlayerEntity()).getId();
            players.add(new Player(playerId));
        }

        List<CardEntity> cardEntities = cardRepository.saveAll(CardPile.createCardEntityPile());
        Stack<Card> cards = new Stack<>();
        for (CardEntity cardEntity: cardEntities) {
            cards.push(new Card(cardEntity));
        }

        Game game = new Game(gameEntity.getId(), players, cards);
        return game;
    }

    public void endGame(int gameId) {
        // delete stuff from repos
    }
}
