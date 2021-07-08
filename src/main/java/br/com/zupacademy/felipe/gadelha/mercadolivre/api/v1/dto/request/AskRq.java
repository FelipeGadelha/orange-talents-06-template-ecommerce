package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AskRq {
	
	private String title;

	@JsonCreator
	public AskRq(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
}
