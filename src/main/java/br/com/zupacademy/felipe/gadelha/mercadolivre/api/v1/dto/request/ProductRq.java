package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.validator.annotation.IsExists;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public class ProductRq {

	@NotNull @NotBlank
	private String name;
	@Positive
	@NotNull
	private BigDecimal price;
	@PositiveOrZero
	@NotNull
	private Integer availableQuantity;
	@NotNull @NotBlank @Length(max = 1000)
	private String description;
	@NotNull
	@IsExists(domainClass = Category.class,
			fieldId = "id",
			message = "Não existe Categoria com este ID")
	private Long categoryId;
	@Size(min = 3)
	private Map<String, String> features;
	
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public Map<String, String> getFeatures() {
		return features;
	}
	public String getDescription() {
		return description;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	
	public Product convert(EntityManager manager, User user) {
		var category = manager.find(Category.class, categoryId);
		Assert.state(Objects.nonNull(category), "Não existe Categoria com esse ID no Banco de Dados");
		return Product
				.builder()
				.name(name)
				.user(user)
				.price(price)
				.availableQuantity(availableQuantity)
				.description(description)
				.features(features)
				.category(category)
				.build();
	}
}
