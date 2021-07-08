package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Opinion;

public class OpinionRs {

	private String title;
	private Integer evaluation;
	private String login;

	public OpinionRs(Opinion opinion) {
		title = opinion.getTitle();
		evaluation = opinion.getEvaluation();
		login = opinion.getUser().getLogin();
	}

	public String getTitle() {
		return title;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public String getLogin() {
		return login;
	}

	
}
