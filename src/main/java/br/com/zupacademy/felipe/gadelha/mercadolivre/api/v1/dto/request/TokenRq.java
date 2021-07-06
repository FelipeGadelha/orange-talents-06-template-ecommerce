package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenRq {

	private String login;
	private String password;

	public TokenRq(String login, String password) {
		this.login = login;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public UsernamePasswordAuthenticationToken convertToAuth() {
		return new UsernamePasswordAuthenticationToken(login, password);
	}
}
