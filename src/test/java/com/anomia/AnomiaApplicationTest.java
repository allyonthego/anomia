package com.anomia;

import com.anomia.controller.AnomiaController;
import com.anomia.controller.reqres.AddWinRequest;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.service.GameService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void WHEN_GetGame_THEN_ReturnGameResponse() throws Exception {
		controller.gameListAdd(createEmptyGame());
		mockMvc
				.perform(get("/games/0"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"id\":0")));
		controller.gameListRemove(0);
	}

	// write out cardCounts
	// gameList still has a game for some reason
	@Test
	void WHEN_EndGame_THEN_ReturnEndGameResponse() throws Exception {
		controller.gameListAdd(createEmptyGame());
		mockMvc
				.perform(delete("/games/0"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"cardCounts\":")));
		assertEquals(0, controller.gameListCount());
	}

	@Test
	void WHEN_PostAddPlayPile_THEN_Return() throws Exception {
		controller.gameListAdd(createEmptyGame());
		mockMvc
			.perform(post("/games/0/0/playPile"))
			.andExpect(status().isOk());
		controller.gameListRemove(0);
	}

	@Test
	void WHEN_PostAddWinPile_THEN_Return() throws Exception {
		Game game = createEmptyGame();
		controller.gameListAdd(game);
		game.addPlayPile(0);
		game.addPlayPile(1);
		mockMvc
				.perform(post("/games/0/0/winPile")
					.content(asJsonString(new AddWinRequest(1)))
					.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
		controller.gameListRemove(0);
	}

	// returns gameId = 1
	@Test
	void WHEN_PostStartGame_THEN_ReturnGameId() throws Exception {
		GameService mockGameService = mock(GameService.class);
		when(mockGameService.startGame(2)).thenReturn(createEmptyGame());
		mockMvc
				.perform(post("/games")
						.content(asJsonString(new StartGameRequest(2)))
						.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("\"gameId\":0")));
		controller.gameListRemove(0);
	}
}
