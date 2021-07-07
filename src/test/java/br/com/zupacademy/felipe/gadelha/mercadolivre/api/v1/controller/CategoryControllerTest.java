package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.CategoryRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.builder.DataBuilder;
import br.com.zupacademy.felipe.gadelha.mercadolivre.builder.TokenAuth;
import br.com.zupacademy.felipe.gadelha.mercadolivre.convert.JacksonParse;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;

@Transactional
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private JacksonParse jackson;
	final String BASE_PATH = "/v1/categories";
	@Autowired
	private DataBuilder builder;
	@PersistenceContext
	private EntityManager manager;
	
	@BeforeEach
	void setUp() {
		manager.createQuery("delete from Category").executeUpdate();
		manager.flush();
		builder.userDataBuilder();
	}
	
	@Test
	@DisplayName("should successfully save a category")
	void test() throws JsonProcessingException, Exception {
		var categoryRq = new CategoryRq("Home", null);
		
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", builder.getToken(TokenAuth.USER))
				.content(jackson.toJson(categoryRq)))
		.andExpect(status().isOk());
		
		var category = manager.createQuery("select c from Category c where c.name =:pName", Category.class)
				.setParameter("pName", categoryRq.getName())
				.getSingleResult();
		assertThat(category.getName()).isEqualTo(categoryRq.getName());
	}
	
	@Test
	@DisplayName("should successfully save a category with parent-category")
	void test01() throws JsonProcessingException, Exception {
		var categoryParentRq = new CategoryRq("Tech", null);
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", builder.getToken(TokenAuth.USER))
				.content(jackson.toJson(categoryParentRq)))
		.andExpect(status().isOk());
		
		var categoryRq = new CategoryRq("Celular", 1L);
		mockMvc.perform(post(BASE_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", builder.getToken(TokenAuth.USER))
				.content(jackson.toJson(categoryRq)))
		.andExpect(status().isOk());
		
		var category = manager.createQuery("select c from Category c where c.name =:pName", Category.class)
				.setParameter("pName", categoryRq.getName())
				.getSingleResult();
		assertThat(category.getName()).isEqualTo(categoryRq.getName());
		assertThat(category.getParentCategory().getId()).isEqualTo(categoryRq.getParentCategoryId());
	}
}

















