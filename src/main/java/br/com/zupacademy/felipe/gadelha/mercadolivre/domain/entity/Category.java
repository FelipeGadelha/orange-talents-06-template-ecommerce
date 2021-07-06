package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @NotBlank
	@Column(nullable = false, unique = true)
	private String name;
	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;
	
	@Deprecated
	public Category() { }
	
	public Category(String name, Category parentCategory) {
		this.name = name;
		this.parentCategory = parentCategory;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parentCategory=" + parentCategory + "]";
	}
}