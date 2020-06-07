package com.anomia;

import com.anomia.controller.AnomiaController;
import com.anomia.controller.reqres.AddWinRequest;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.state.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.anomia.helper.Helper.asJsonString;
import static com.anomia.helper.Helper.createEmptyGame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

	int numPlayers = 2;
	int gameId = 1;

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
		controller.postGame(new StartGameRequest(numPlayers));
		mockMvc
				.perform(delete("/games/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"cardCounts\":")));
		assertEquals(0, controller.gameListCount());
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
		mockMvc
				.perform(post("/games")
						.content(asJsonString(new StartGameRequest(numPlayers)))
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"gameId\":1")));
		controller.deleteGame(gameId);
	}
}
