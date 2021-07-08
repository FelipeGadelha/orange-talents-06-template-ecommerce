package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Ask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull @NotBlank
	private String title;
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	@CreationTimestamp @PastOrPresent
	@Column(name="creation_date", nullable = false)	
	private LocalDateTime creationDate;
	
	@Deprecated
	public Ask() {	}
	
	public Ask(String title, User user, Product product) {
		this.title = title;
		this.user = user;
		this.product = product;
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public User getUser() {
		return user;
	}
	public Product getProduct() {
		return product;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	@Override
	public String toString() {
		return "Ask [id=" + id + ", title=" + title + ", user=" + user + ", product=" + product + ", creationDate="
				+ creationDate + "]";
	}
}
