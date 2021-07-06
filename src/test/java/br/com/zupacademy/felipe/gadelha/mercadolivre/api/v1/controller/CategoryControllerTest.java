package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.CategoryRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;

@Transactional
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {
	
	@LocalServerPort
	private int port;
	final String BASE_PATH = "/v1/categories";
	
	@Autowired
	private TestRestTemplate restTemplate;

	@PersistenceContext
	private EntityManager manager;
	
//	private static final User USER = new User("user@email.com", 
//				"123456", 
//				List.of(new Profile("ROLE_USER")));
//	
//	private static final User ADMIN = new User("admin@email.com", 
//				"123456", 
//				List.of(new Profile("ROLE_ADMIN")));
//	private ResponseEntity<TokenRs> TOKEN_USER;
//	private ResponseEntity<TokenRs> TOKEN_ADMIN;
	
	@BeforeEach
	void setUp() {
		manager.createQuery("delete from Category").executeUpdate();
		manager.flush();
//		var userRq = new UserRq("user@email.com", "123456");
//		TOKEN_USER = restTemplate.postForEntity("/v1/users", userRq, TokenRs.class);
//		var adminRq = new UserRq("admin@email.com", "123456");
//		TOKEN_ADMIN = restTemplate.postForEntity("/v1/users", adminRq, TokenRs.class);
	}
	
	@Test
	@DisplayName("should successfully save a category")
	void test() {
		var categoryRq = new CategoryRq("Home", null);
		ResponseEntity<?> entity = restTemplate.postForEntity(BASE_PATH, categoryRq, null);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		
		var category = manager.createQuery("select c from Category c where c.name =:pName", Category.class)
				.setParameter("pName", categoryRq.getName())
				.getSingleResult();
		assertThat(category.getName()).isEqualTo(categoryRq.getName());
	}
	
	@Test
	@DisplayName("should successfully save a category with parent-category")
	void test01() {
		var categoryParentRq = new CategoryRq("Tech", null);
		restTemplate.postForEntity(BASE_PATH, categoryParentRq, null);
		var categoryRq = new CategoryRq("Celular", 1L);
		ResponseEntity<?> entity = restTemplate.postForEntity(BASE_PATH, categoryRq, null);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		
		var category = manager.createQuery("select c from Category c where c.name =:pName", Category.class)
				.setParameter("pName", categoryRq.getName())
				.getSingleResult();
		assertThat(category.getName()).isEqualTo(categoryRq.getName());
		assertThat(category.getParentCategory().getId()).isEqualTo(categoryRq.getParentCategoryId());
	}
	
}
