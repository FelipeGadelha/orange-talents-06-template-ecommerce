package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.validator.annotation.UniqueValue;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Profile;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public class UserRq {

	@Email @NotBlank 
	@UniqueValue(domainClass = User.class,
			fieldName = "login",
			message = "Não é possível realizar um cadastro com este email.")
	private String login;
	@NotBlank @Size(min = 6)
	private String password;

	public UserRq(String login, @NotBlank @Size(min = 6) String password) {
		this.login = login;
		this.password = encrypt(password);
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public User convert(EntityManager manager) {
		Profile profile = manager.find(Profile.class, 1L);
		return new User(login, password, Arrays.asList(profile));
	}
	private String encrypt(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}
