package br.com.zupacademy.felipe.gadelha.mercadolivre.builder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.zupacademy.felipe.gadelha.mercadolivre.convert.JacksonParse;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Profile;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

@Component
public class DataBuilder {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private JacksonParse jackson;
	
	private String typeToken = "Bearer ";
	
	private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	public void userDataBuilder() {
		var profileUser = new Profile("ROLE_USER");
		manager.persist(profileUser);
		
		String encode = bCrypt.encode("123456");
		var user = new User("user@email.com", encode, List.of(profileUser));
		manager.persist(user);
	}
	
	public void adminDataBuilder() {
		var profileAdmin = new Profile("ROLE_ADMIN");
		manager.persist(profileAdmin);
		
		String encode = bCrypt.encode("123456");
		var admin = new User("admin@email.com", encode, List.of(profileAdmin));
		manager.persist(admin);
	}
	
	public String getToken(TokenAuth tokenAuth) throws JsonProcessingException, Exception {
		
		ResultActions resultActions = mockMvc.perform(post("/v1/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(tokenAuth.getTokenRq())))
		.andExpect(status().isOk());
		
		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		String token = jackson.toMap(contentAsString).get("token").toString();
		return typeToken + token;
	}
	
}
