package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.UserRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.convert.JacksonParse;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

@Transactional
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {
	
	final String BASE_PATH = "/v1/users";
	
	@Autowired
	private MockMvc mockMvc;
	@PersistenceContext
	private EntityManager manager;	
	@Autowired
	private JacksonParse jackson;
//	@Autowired
//	private DataBuilder builder;
	
	@BeforeEach
	void setUp() {
		manager.createQuery("delete from User").executeUpdate();
		manager.flush();
	}
	
	@Test
	@DisplayName("should successfully save a user")
	void test() throws JsonProcessingException, Exception {
		String password = "123456";
		var userRq = new UserRq("felipe@email.com", password);
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(userRq)))
		.andExpect(status().isOk());
		
		User user = manager.createQuery("select u from User u where u.login =:pLogin", User.class)
				.setParameter("pLogin", userRq.getLogin())
				.getSingleResult();
		assertThat(user.getLogin()).isEqualTo(userRq.getLogin());
		assertNotNull(user.getPassword());
	}
}
