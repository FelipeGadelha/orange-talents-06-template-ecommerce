package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "images")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@URL
	@NotBlank
	private String link;
	@ManyToOne
	@NotNull
	@Valid
	private Product product;
	
	@Deprecated
	public Image() { }
	
	public Image(String link, Product product) {
		this.link = link;
		this.product = product;
	}
	public Long getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", link=" + link + "]";
	}
}
