package com.anomia;

import com.anomia.controller.state.Card;
import com.anomia.controller.state.Game;
import com.anomia.controller.state.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static com.anomia.controller.state.CardColour.BLUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private int playerId = 0;
    private int numCards = 5;
    private int numPlayers = 4;

    // Step 1
    @Test
    public void WHEN_StartGame_THEN_GamePopulated() {
        Game game = new Game(numPlayers);

        assertEquals(0, game.getId());
        assertTrue(drawPilePopulated(game.getDrawPile()));
        assertTrue(playersPopulated(game.getPlayers()));

        Game.decrCount();
    }

    public boolean drawPilePopulated(Stack<Card> drawPile) {
        assertEquals(numCards, drawPile.size());
        Card card = drawPile.peek();
        assertEquals(numCards - 1, card.getId());
        assert(!card.isReveal());
        assertEquals(BLUE, card.getColour());
        assertEquals("Test", card.getWord());
        return true;
    }

    public boolean playersPopulated(ArrayList<Player> players) {
        assertEquals(numPlayers, players.size());
        assertEquals(playerId, players.get(playerId).getId());
        return true;
    }

    // Step 2
    @Test
    public void WHEN_AddPlayPile_THEN_DrawPopPlayPush() {
        Game game = new Game(numPlayers);

        game.addPlayPile(playerId);
        assertEquals(numCards - 1, game.getDrawPile().size());
        Stack<Card> playPile = game.getPlayerById(playerId).getPlayPile();
        assertEquals(1, playPile.size());
        assertEquals(true, playPile.peek().isReveal());

        Game.decrCount();
    }

    // Step 3
    @Test
    public void WHEN_AddWinPile_THEN_PlayPopPlayPush() {
        Game game = new Game(numPlayers);

        game.addPlayPile(playerId);
        game.addPlayPile(playerId + 1);
        game.addPlayPile(playerId + 1);
        game.addWinPile(playerId, playerId + 1);

        Stack<Card> playPile = game.getPlayerById(playerId).getPlayPile();
        Stack<Card> winPile = game.getPlayerById(playerId).getWinPile();
        Stack<Card> oppoPlayPile = game.getPlayerById(playerId + 1).getPlayPile();

        assertEquals(1, playPile.size());
        assertEquals(1, winPile.size());
        assertEquals(1, oppoPlayPile.size());
        assertTrue(oppoPlayPile.peek().isReveal());
    }
}
