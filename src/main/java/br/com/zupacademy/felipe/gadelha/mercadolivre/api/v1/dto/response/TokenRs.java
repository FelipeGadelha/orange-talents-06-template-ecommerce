package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

public class TokenRs {
	
	private String token;
	private String type;

	public TokenRs(String token, String type) {
		this.token = token;
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public String getType() {
		return type;
	}
}
