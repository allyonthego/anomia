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

    public void endGame(int gameId) {
        cardRepository.deleteByGameId(gameId);
        playerRepostiory.deleteByGameId(gameId);
        gameRepository.deleteById(gameId);
    }

    public void endSaveGame(Game game) {
        gameRepository.save(new GameEntity(game));

        for (Player player: game.getPlayers().values()) {
            playerRepostiory.save(new PlayerEntity(game, player));

            for (Card card: player.getPlayPile()) {
                cardRepository.save(new CardEntity(game.getId(), player.getId(), card, 2));
            }
            for (Card card: player.getWinPile()) {
                cardRepository.save(new CardEntity(game.getId(), player.getId(), card, 3));
            }
        }

        for (Card card: game.getDrawPile()) {
            cardRepository.save(new CardEntity(game.getId(), 0, card, 1));
        }
    }

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
        for (CardEntity cardEntity : cardEntities) {
            drawPile.push(new Card(cardEntity));
        }

        Game game = new Game(gameId, players, drawPile);
        return game;
    }

    public Game startSaveGame(int gameId) {
        GameEntity gameEntity = gameRepository.findAllById(gameId).get(0);

        List<PlayerEntity> playerEntities = playerRepostiory.findAllByGameId(gameId);
        HashMap<Integer, Player> players = new HashMap<>();
        for (PlayerEntity playerEntity : playerEntities) {
            int playerId = playerEntity.getId();

            Stack<Card> playPile = cardFromRespository(gameId, 2);
            Stack<Card> winPile = cardFromRespository(gameId, 3);

            players.put(playerId, new Player(playerId, playPile, winPile));
        }

        Stack<Card> drawPile = cardFromRespository(gameId, 1);

        Game game = new Game(gameEntity.getId(), players, drawPile);
        return game;
    }

    private Stack<Card> cardFromRespository(int gameId, int whichPile) {
        Stack<Card> cardPile = new Stack<>();
        List<CardEntity> cardEntities;

        cardEntities = cardRepository.findAllByGameIdAndWhichPileAndIsReveal(gameId, whichPile, false);

        for (CardEntity cardEntity : cardEntities) {
            cardPile.push(new Card(cardEntity));
        }

        if (whichPile == 2) {
            cardEntities =
                    cardRepository.findAllByGameIdAndWhichPileAndIsReveal(gameId, whichPile, true);
            cardPile.push(new Card(cardEntities.get(0)));
        }
        return cardPile;
    }
}
