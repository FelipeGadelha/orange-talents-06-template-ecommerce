package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import java.time.LocalDateTime;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;

public class AskRs {

	private String title;
	private String username;
	private LocalDateTime creationDate;

	public AskRs(Ask ask) {
		title = ask.getTitle();
		username = ask.getUser().getUsername();
		creationDate = ask.getCreationDate();
	}
	public String getTitle() {
		return title;
	}
	public String getUsername() {
		return username;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
