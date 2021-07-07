package br.com.zupacademy.felipe.gadelha.mercadolivre.builder;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.TokenRq;

public enum TokenAuth {

	USER(new TokenRq("user@email.com", "123456")),
	ADMIN(new TokenRq("admin@email.com", "123456"));

	private TokenRq tokenRq;

	TokenAuth(TokenRq tokenRq) {
		this.tokenRq = tokenRq;
	}
	public TokenRq getTokenRq() {
		return tokenRq;
	}
}
