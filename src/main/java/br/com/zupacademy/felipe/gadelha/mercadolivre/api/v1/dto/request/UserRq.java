package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public class UserRq {

	@Email @NotBlank
	private String login;
	@NotBlank @Size(min = 6)
	private String password;

	public UserRq(String login, String password) {
		this.login = login;
		this.password = encrypt(password);
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public User convert() {
		return new User(login, password);
	}
	private String encrypt(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}