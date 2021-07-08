package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Opinion;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public class OpinionRq {
	
	@NotNull
	@Range(min = 1, max = 5)
	private Integer evaluation;
	@NotNull @NotBlank
	private String title;
	@NotNull @NotBlank
	@Size(max = 500)
	private String description;
	
	public Integer getEvaluation() {
		return evaluation;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}

	public Opinion convert(User user, Product product) {
		return new Opinion(evaluation, title, description, user, product);
	}
	
	
	
	
}
