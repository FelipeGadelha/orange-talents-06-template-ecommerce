package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.UserRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {
	
	@LocalServerPort
	private int port;
	final String BASE_PATH = "/v1/users";
	
	@Autowired
	private TestRestTemplate restTemplate;

	@PersistenceContext
	private EntityManager manager;
	
	@BeforeEach
	void setUp() {
		manager.createQuery("delete from User u");
	}
	
	@Test
	@DisplayName("should successfully save a user")
	void test() {
		String password = "123456";
		var userRq = new UserRq("felipe@email.com", password);
		ResponseEntity<?> entity = restTemplate.postForEntity(BASE_PATH, userRq, null);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		
		User user = manager.createQuery("select u from User u where u.login =:pLogin", User.class)
				.setParameter("pLogin", userRq.getLogin())
				.getSingleResult();
		assertThat(user.getLogin()).isEqualTo(userRq.getLogin());
		assertNotNull(user.getPassword());
	}
	

}
