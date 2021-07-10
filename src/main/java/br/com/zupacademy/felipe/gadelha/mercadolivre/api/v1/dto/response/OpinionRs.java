package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Opinion;

public class OpinionRs {

	private String title;
	private Integer evaluation;
	private String username;

	public OpinionRs(Opinion opinion) {
		title = opinion.getTitle();
		evaluation = opinion.getEvaluation();
		username = opinion.getUser().getUsername();
	}

	public String getTitle() {
		return title;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public String getUsername() {
		return username;
	}

}
