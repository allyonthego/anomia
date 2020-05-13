package com.anomia;

import com.anomia.rest.state.Card;
import com.anomia.rest.state.Game;
import org.junit.jupiter.api.Test;

import static com.anomia.rest.state.CardColour.BLUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game = new Game();
    private int numPlayers = 4;

    @Test
    public void WHEN_StartGame_THEN_GameIdPopulated() {
        game.startGame(numPlayers);
        assertEquals(0, game.getId());
    }

    @Test
    public void WHEN_StartGame_THEN_DrawPilePopulated() {
        game.startGame(numPlayers);
        assertEquals(1, game.getDrawPile().size());
        Card card = game.getDrawPile().peek();
        assertEquals(0, card.getId());
        assert(!card.isReveal());
        assertEquals(BLUE, card.getColour());
        assertEquals("Test", card.getWord());
    }

    @Test
    public void WHEN_StartGame_THEN_PlayersPopulated() {
        game.startGame(numPlayers);
        assertEquals(numPlayers, game.getPlayers().size());
        assertEquals(0, game.getPlayers().get(0).getId());
    }
}
