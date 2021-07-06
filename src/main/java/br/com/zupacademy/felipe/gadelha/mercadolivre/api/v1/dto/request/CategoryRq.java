package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.validator.annotation.UniqueValue;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.CategoryRepository;

public class CategoryRq {

	@NotNull @NotBlank
	@UniqueValue(domainClass = Category.class,
			fieldName = "name",
			message = "Já existe uma categoria cadastrado com esse nome")
	private String name;
	
	@Positive
	private Long parentCategoryId;
	
	public CategoryRq(String name, Long parentCategoryId) {
		this.name = name;
		this.parentCategoryId = parentCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public Category convert(CategoryRepository repository) {
		if (Objects.nonNull(parentCategoryId)) {
			Optional<Category> optional = repository.findById(parentCategoryId);
			Assert.state(optional.isPresent(), "Não existe categoria com esse ID: " + parentCategoryId);
			return new Category(name, optional.get());
		}
		return new Category(name, null);
	}
}
