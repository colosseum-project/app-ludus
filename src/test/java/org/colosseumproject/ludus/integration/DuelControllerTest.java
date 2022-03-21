package org.colosseumproject.ludus.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/snippets")
@TestMethodOrder(OrderAnnotation.class)
public class DuelControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	public void shouldEngageAndReturnResult() throws Exception {
		mvc.perform(post("/duels/resolve")
				.param("first_gladiator_id", "1")
				.param("second_gladiator_id", "2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document("duels/resolve"));
	}

	@Test
	@Order(2)
	public void shouldNotEngageWithSameGladiator() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			mvc.perform(post("/duels/resolve")
					.param("first_gladiator_id", "1")
					.param("second_gladiator_id", "1"));
		});
		assertTrue(exception.getMessage().contains("Same gladiator."));
	}

	@Test
	@Order(3)
	public void shouldFindAllDuelResultsandReturnJson() throws Exception {
		mvc.perform(get("/duels/results"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("duels/results/find-all"));
	}

	@Test
	@Order(4)
	public void shouldFindTheFirstDuelResult() throws Exception {
		mvc.perform(get("/duels/results/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("duels/results/find-one"));
	}

}
