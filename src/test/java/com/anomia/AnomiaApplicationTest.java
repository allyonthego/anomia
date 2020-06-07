package com.anomia;

import com.anomia.controller.AnomiaController;
import com.anomia.controller.database.*;
import com.anomia.controller.reqres.AddWinRequest;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.reqres.StartGameResponse;
import com.anomia.controller.state.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.anomia.helper.Helper.asJsonString;
import static com.anomia.helper.Helper.createEmptyGame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnomiaApplicationTest {

	@Autowired
	private AnomiaController controller;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PlayerRepostiory playerRepostiory;

	int winId = 1;
	int loseId = 2;
	int numPlayers = 2;
	int gameId = 1;
	int numCards = 20;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void WHEN_GetGame_THEN_ReturnGameResponse() throws Exception {
		controller.gameListAdd(createEmptyGame());
		mockMvc
				.perform(get("/games/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"id\":1")));
		controller.gameListRemove(gameId);
	}

	@Test
	void WHEN_EndGame_THEN_ReturnEndGameResponse() throws Exception {
		StartGameResponse res = controller.postGame(new StartGameRequest(numPlayers));
		mockMvc
				.perform(delete("/games/"+res.getGameId()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"cardCounts\":")));
		assertTrue(endGameCheckRepo());
	}

	@Test
	void WHEN_EndSaveGame_THEN_ReturnEndGameResponse() throws Exception {
		StartGameResponse res = controller.postGame(new StartGameRequest(numPlayers));
		scenario(res.getGameId());
		mockMvc
				.perform(delete("/games/"+res.getGameId()+"/save"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"cardCounts\":")));
		assertEquals(0, controller.gameListCount());
		assertTrue(endSaveGameCheckRepo(res.getGameId()));
	}

	@Test
	void WHEN_PostAddPlayPile_THEN_Return() throws Exception {
		controller.gameListAdd(createEmptyGame());
		mockMvc
			.perform(post("/games/1/1/playPile"))
			.andExpect(status().isOk());
		controller.gameListRemove(gameId);
	}

	@Test
	void WHEN_PostAddWinPile_THEN_Return() throws Exception {
		Game game = createEmptyGame();
		controller.gameListAdd(game);
		game.addPlayPile(1);
		game.addPlayPile(2);
		mockMvc
				.perform(post("/games/1/1/winPile")
					.content(asJsonString(new AddWinRequest(2)))
					.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
		controller.gameListRemove(gameId);
	}

	@Test
	void WHEN_PostStartGame_THEN_ReturnGameId() throws Exception {
		// gameId might not be 1
		mockMvc
				.perform(post("/games")
						.content(asJsonString(new StartGameRequest(numPlayers)))
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"gameId\":1")));
		assertTrue(startGameCheckRepo());
		controller.deleteGame(gameId);
	}

	@Test
	void WHEN_PostStartSaveGame_THEN_ReturnGameId() throws Exception {
		StartGameResponse res = controller.postGame(new StartGameRequest(numPlayers));
		int gameId = res.getGameId();
		scenario(gameId);
		controller.deleteSaveGame(gameId);
		mockMvc
				.perform(post("/games/"+gameId+"/save")
						.content(asJsonString(new StartGameRequest(numPlayers)))
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"gameId\":"+gameId)));

		assertEquals(1, controller.gameListCount());
		Game game = controller.gameListGet(gameId);
		// write unit tests for game

		controller.deleteGame(gameId);
	}

	// should be unit tests
	private boolean endGameCheckRepo() {
		assertEquals(0, controller.gameListCount());
		assertEquals(0, cardRepository.findAllByGameId(gameId).size());
		assertEquals(0, playerRepostiory.findAllByGameId(gameId).size());
		assertEquals(0, gameRepository.findAllById(gameId).size());
		return true;
	}
	private void scenario(int gameId) {
		controller.postAddPlayPile(gameId,winId);
		controller.postAddPlayPile(gameId,winId);
		controller.postAddPlayPile(gameId,loseId);
		controller.postAddWinPile(gameId, winId, new AddWinRequest(loseId));
	}
	private boolean endSaveGameCheckRepo(int gameId) {
		List<CardEntity> cardEntities = cardRepository.findAllByGameId(gameId);
		List<PlayerEntity> playerEntities = playerRepostiory.findAllByGameId(gameId);
		List<GameEntity> gameEntities = gameRepository.findAllById(gameId);

		assertEquals(numCards,cardEntities.size());
		assertEquals(numPlayers,playerEntities.size());
		assertEquals(1,gameEntities.size());

		int gameEntityId = gameEntities.get(0).getId();
		PlayerEntity playerEntity = playerEntities.get(0);
		CardEntity wonCard = cardEntities.get(numCards-1);
		CardEntity topPlayCard = cardEntities.get(numCards-2);
		CardEntity secondPlayCard = cardEntities.get(numCards-3);

		assertEquals(gameEntityId,playerEntity.getGameId());

		assertEquals(gameEntityId,wonCard.getGameId());
		assertEquals(1,wonCard.getPlayerId());
		assertTrue(wonCard.isReveal());
		assertEquals(3,wonCard.getWhichPile());

		assertEquals(gameEntityId,topPlayCard.getGameId());
		assertEquals(1,topPlayCard.getPlayerId());
		assertTrue(topPlayCard.isReveal());
		assertEquals(2,topPlayCard.getWhichPile());

		assertEquals(gameEntityId,secondPlayCard.getGameId());
		assertEquals(1,secondPlayCard.getPlayerId());
		assertTrue(!secondPlayCard.isReveal());
		assertEquals(2,secondPlayCard.getWhichPile());

		return true;
	}
	private boolean startGameCheckRepo() {
		List<CardEntity> cardEntities = cardRepository.findAllByGameId(gameId);
		List<PlayerEntity> playerEntities = playerRepostiory.findAllByGameId(gameId);
		List<GameEntity> gameEntities = gameRepository.findAllById(gameId);

		assertEquals(numCards,cardEntities.size());
		assertEquals(1,gameEntities.size());
		assertEquals(2,playerEntities.size());

		int gameEntityId = gameEntities.get(0).getId();
		PlayerEntity playerEntity = playerEntities.get(0);
		CardEntity cardEntity = cardEntities.get(0);

		assertEquals(gameEntityId,playerEntity.getGameId());
		assertEquals(gameEntityId,cardEntity.getGameId());
		assertEquals(0,cardEntity.getPlayerId());
		assertTrue(!cardEntity.isReveal());
		assertEquals(1,cardEntity.getWhichPile());

		return true;
	}
}
