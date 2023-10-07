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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
						fieldWithPath("[].name").description("Name of the gladiator"),
						fieldWithPath("[].type").description("Type of gladiator"))));
	}

	@Test
	@Order(2)
	public void shouldFindTheFirstGladiator() throws Exception {
		mvc.perform(get("/gladiators/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json("{\"id\":1,\"name\":\"Maxentius\"},\"type\":\"MURMILLO\"}"))
				.andDo(document("gladiators/find-one", responseFields(
						fieldWithPath("id").description("ID of the gladiator"),
						fieldWithPath("name").description("Name of the gladiator"),
						fieldWithPath("type").description("Type of gladiator"))));
	}

	@Test
	@Order(3)
	public void shouldFindTheSecondGladiator() throws Exception {
		mvc.perform(get("/gladiators/2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json("{\"id\":2,\"name\":\"Spartacus\",\"type\":\"MURMILLO\"}"));
	}

	@Test
	@Order(4)
	public void shouldFindTheThirdGladiatorByName() throws Exception {
		mvc.perform(get("/gladiators/name/Atius"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":3,\"name\":\"Atius\",\"type\":\"THRAEX\"}"))
				.andDo(document("gladiators/find-one-by-name", responseFields(
						fieldWithPath("id").description("ID of the gladiator"),
						fieldWithPath("name").description("Name of the gladiator"),
						fieldWithPath("type").description("Type of gladiator"))));
	}

	@Test
	@Order(5)
	public void shouldDeleteTheThirdGladiator() throws Exception {
		mvc.perform(delete("/gladiators/3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":3,\"name\":\"Atius\",\"type\":\"THRAEX\"}"))
				.andDo(document("gladiators/delete-one"));
	}

	@Test
	@Order(6)
	public void shouldEditTheFourthGladiator() throws Exception {
		mvc.perform(put("/gladiators/4").contentType("application/json").content("{\"name\":\"Hicarus\"}"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":4,\"name\":\"Hicarus\",\"type\":\"DIMACHAERUS\"}"))
				.andDo(document("gladiators/edit-one"));
	}

	@Test
	@Order(7)
	public void shouldGetGladiatorCount() throws Exception {
		mvc.perform(get("/gladiators/count"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("21"))
				.andDo(document("gladiators/count"));
	}

	@Test
	@Order(8)
	public void shouldGetGladiatorTypes() throws Exception {
		mvc.perform(get("/gladiators/types"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("MURMILLO")))
				.andDo(document("gladiators/types"));
	}

	// TODO Fix shouldAddNewGladiator broken test
	/*
	 *
	 * @Test
	 *
	 * @Order(9)
	 * public void shouldAddNewGladiator() throws Exception {
	 * mvc.perform(post("/gladiators").contentType("application/json")
	 * .content("{\"name\":\"Testus\",\"type\":\"MURMILLO\"}"))
	 * .andDo(print())
	 * .andExpect(status().isOk())
	 * .andExpect(content().json(
	 * "{\"id\":23,\"name\":\"Testus\",\"type\":\"MURMILLO\"}"))
	 * .andDo(document("gladiators/new-one"));
	 * }
	 */

}
