package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import java.time.LocalDateTime;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;

public class AskRs {

	private String title;
	private String login;
	private LocalDateTime creationDate;

	public AskRs(Ask ask) {
		title = ask.getTitle();
		login = ask.getUser().getLogin();
		creationDate = ask.getCreationDate();
	}
	public String getTitle() {
		return title;
	}
	public String getLogin() {
		return login;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
