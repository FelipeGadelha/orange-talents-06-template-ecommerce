package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.TokenRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.builder.DataBuilder;

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private DataBuilder builder;	
	final String BASE_PATH = "/v1/auth";
	
	@PersistenceContext
	private EntityManager manager;
	
	@BeforeEach
	void setUp() throws JsonProcessingException, Exception {
		manager.createQuery("delete from User").executeUpdate();
		manager.flush();
		builder.adminDataBuilder();
	}
	@Test
	@DisplayName("should authenticate a user successfully")
	void test() throws Exception {
		var tokenRq = new TokenRq("admin@email.com", "123456");
		System.out.println(toJson(tokenRq));
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(tokenRq)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.token", Matchers.notNullValue()))
		.andExpect(jsonPath("$.type", Matchers.equalTo("Bearer")));
	}
	@Test
	@DisplayName("should return 400 per username or password invalid")
	void test1() throws Exception {
		var tokenRq = new TokenRq("wrong@email.com", "123456");
		System.out.println(toJson(tokenRq));
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(tokenRq)))
		.andExpect(status().isBadRequest());
	}
	
	private String toJson(TokenRq tokenRq) throws JsonProcessingException {
		return mapper.writeValueAsString(tokenRq);
	}
	
}
