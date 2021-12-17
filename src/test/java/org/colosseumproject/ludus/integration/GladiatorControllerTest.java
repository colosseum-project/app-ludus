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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/snippets")
@TestMethodOrder(OrderAnnotation.class)
public class GladiatorControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	public void shouldReturnJson() throws Exception {
		mvc.perform(get("/gladiators"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("gladiators/find-all", responseFields(
						fieldWithPath("[].id").description("ID of the gladiator"),
						fieldWithPath("[].name").description("Name of the gladiator"))));
	}

	@Test
	@Order(2)
	public void shouldFindTheFirstGladiator() throws Exception {
		mvc.perform(get("/gladiators/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json("{\"id\":1,\"name\":\"Maxentius\"}"))
				.andDo(document("gladiators/find-one", responseFields(
						fieldWithPath("id").description("ID of the gladiator"),
						fieldWithPath("name").description("Name of the gladiator"))));
	}

	@Test
	@Order(3)
	public void shouldFindTheSecondGladiator() throws Exception {
		mvc.perform(get("/gladiators/2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json("{\"id\":2,\"name\":\"Spartacus\"}"));
	}

	@Test
	@Order(4)
	public void shouldFindTheFifthGladiatorByName() throws Exception {
		mvc.perform(get("/gladiators/name/Bonus"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":5,\"name\":\"Bonus\"}"))
				.andDo(document("gladiators/find-one-by-name", responseFields(
						fieldWithPath("id").description("ID of the gladiator"),
						fieldWithPath("name").description("Name of the gladiator"))));
	}

	@Test
	@Order(5)
	public void shouldAddNewGladiator() throws Exception {
		mvc.perform(post("/gladiators").contentType("application/json").content("{\"name\":\"Testus\"}"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":23,\"name\":\"Testus\"}"))
				.andDo(document("gladiators/new-one"));
	}

	@Test
	@Order(6)
	public void shouldDeleteTheThirdGladiator() throws Exception {
		mvc.perform(delete("/gladiators/3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":3,\"name\":\"Atius\"}"))
				.andDo(document("gladiators/delete-one"));
	}

	@Test
	@Order(7)
	public void shouldEditTheFourthGladiator() throws Exception {
		mvc.perform(put("/gladiators/4").contentType("application/json").content("{\"name\":\"Hicarus\"}"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":4,\"name\":\"Hicarus\"}"))
				.andDo(document("gladiators/edit-one"));
	}

	@Test
	@Order(8)
	public void shouldGetGladiatorCount() throws Exception {
		mvc.perform(get("/gladiators/count"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("22"))
				.andDo(document("gladiators/count"));
	}

}
