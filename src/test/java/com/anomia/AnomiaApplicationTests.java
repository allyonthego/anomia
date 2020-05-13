package com.anomia;

import com.anomia.rest.AnomiaController;
import com.anomia.rest.request.StartGameRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.anomia.helper.Helper.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnomiaApplicationTests {

	@Autowired
	private AnomiaController controller;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void WHEN_PostStartGame_THEN_ReturnGameId() throws Exception {
		mockMvc
			.perform(post("/start")
				.content(asJsonString(new StartGameRequest(4)))
				.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("0")));
	}
}
