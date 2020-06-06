package com.anomia;

import com.anomia.controller.AnomiaController;
import com.anomia.controller.reqres.StartGameRequest;
import com.anomia.controller.state.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.anomia.helper.Helper.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnomiaApplicationTest {

	@Autowired
	private AnomiaController controller;
	@Autowired
	private MockMvc mockMvc;
	private int numPlayers = 4;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void WHEN_PostStartGame_THEN_ReturnGameId() throws Exception {
		mockMvc
			.perform(post("/games")
				.content(asJsonString(new StartGameRequest(numPlayers)))
				.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"gameId\":0")));
	}

	@Test
	void WHEN_GetGame_THEN_ReturnGameResponse() throws Exception {
		controller.gameListAdd(new Game(numPlayers));
		mockMvc
			.perform(get("/games/0"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":0")));
		controller.gameListRemove();
	}

	@Test
	void WHEN_PostAddPlayPile_THEN_ReturnPlayer() throws Exception {
		controller.gameListAdd(new Game(numPlayers));
		mockMvc
			.perform(post("/games/0/0/playPile"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":0")));
		controller.gameListRemove();
	}
}
