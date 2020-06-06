package com.anomia;

import com.anomia.controller.state.Card;
import com.anomia.controller.state.Game;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static com.anomia.controller.state.CardColour.BLUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private int playerId = 0;
    private int numCards = 5;
    private int numPlayers = 4;

    // Step 1
    private Game game = new Game(numPlayers);

    @Test
    public void WHEN_StartGame_THEN_GameIdPopulated() {
        assertEquals(0, game.getId());
    }

    @Test
    public void WHEN_StartGame_THEN_DrawPilePopulated() {
        assertEquals(numCards, game.getDrawPile().size());
        Card card = game.getDrawPile().peek();
        assertEquals(numCards - 1, card.getId());
        assert(!card.isReveal());
        assertEquals(BLUE, card.getColour());
        assertEquals("Test", card.getWord());
    }

    @Test
    public void WHEN_StartGame_THEN_PlayersPopulated() {
        assertEquals(numPlayers, game.getPlayers().size());
        assertEquals(playerId, game.getPlayers().get(playerId).getId());
    }

    // Step 2
    @Test
    public void WHEN_AddPlayPile_THEN_DrawPilePop() {
        game.addPlayPile(playerId);
        assertEquals(numCards - 1, game.getDrawPile().size());
        Stack<Card> playPile = game.getPlayerById(playerId).getPlayPile();
        assertEquals(1, playPile.size());
        assertEquals(true, playPile.peek().isReveal());
    }
}
