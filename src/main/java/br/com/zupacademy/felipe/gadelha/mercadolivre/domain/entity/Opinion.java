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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "opinions")
public class Opinion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @Range(min = 1, max = 5)
	@Column(nullable = false)
	private Integer evaluation;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String title;
	@NotNull @NotBlank @Size(max = 500)
	@Column(nullable = false)
	private String description;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	@NotNull
	@ManyToOne
	@JsonBackReference
	@JoinColumn(nullable = false)
	private Product product;
	
	@Deprecated
	public Opinion() {	}

	public Opinion(@NotNull @Range(min = 1, max = 5) Integer evaluation, 
			@NotNull @NotBlank String title,
			@NotNull @NotBlank @Size(max = 500) String description, 
			User user, 
			Product product) {
				this.evaluation = evaluation;
				this.title = title;
				this.description = description;
				this.user = user;
				this.product = product;
	}
	public Integer getEvaluation() {
		return evaluation;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public User getUser() {
		return user;
	}
	public Product getProduct() {
		return product;
	}
	@Override
	public String toString() {
		return "Opinion [evaluation=" + evaluation + ", title=" + title + ", description=" + description + ", user="
				+ user + ", product=" + product + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opinion other = (Opinion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
