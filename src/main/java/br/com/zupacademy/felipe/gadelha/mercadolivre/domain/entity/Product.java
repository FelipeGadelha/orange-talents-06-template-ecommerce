package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotBlank
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@NotNull
	@JoinColumn(nullable = false)
	private User user;
	@Positive
	@NotNull
	@Column(nullable = false)
	private BigDecimal price;
	
	@PositiveOrZero
	@NotNull
	@Column(nullable = false)
	private Integer availableQuantity;

	@Size(min = 3)
	@ElementCollection
	@MapKeyColumn(name = "description")
	private Map<String, String> features;
	
	@NotNull @NotBlank @Length(max = 1000)
	@Column(nullable = false)
	private String description;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne
	private Category category;
	
	@PastOrPresent
	@CreationTimestamp
	private LocalDateTime registrationDate;

	
	@Deprecated
	public Product() {	}

	public Product(Builder builder) {
		this.name = builder.name;
		this.user = builder.user;
		this.price = builder.price;
		this.availableQuantity = builder.availableQuantity;
		this.features = builder.features;
		this.description = builder.description;
		this.category = builder.category;
	}
	public static Builder builder() {
        return new Builder();
	}
	public static class Builder {
		
		private @NotNull String name;
		private @NotNull User user;
		private @Positive @NotNull BigDecimal price;
		private @PositiveOrZero @NotNull Integer availableQuantity;
		private @NotNull @Length(max = 1000) String description;
		private @NotNull Category category;
		private @Size(min = 3) Map<String, String> features;

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder user(User user) {
			this.user = user;
			return this;
		}
		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		public Builder availableQuantity(Integer availableQuantity) {
			this.availableQuantity = availableQuantity;
			return this;
		}
		public Builder features(Map<String, String> features) {
			this.features = features;
			return this;
		}
		public Builder category(Category category) {
			this.category = category;
			return this;
		}
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		public Product build() {
			return new Product(this);
		}
		
	}
	
	public Long getId() {
		return id;
	}
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
	public Category getCategory() {
		return category;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", availableQuantity=" + availableQuantity
				+ ", features=" + features + ", description=" + description + ", category=" + category
				+ ", registrationDate=" + registrationDate + "]";
	}
}
