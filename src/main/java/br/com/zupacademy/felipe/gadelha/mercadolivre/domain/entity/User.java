package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email @NotBlank
	@Column(nullable = false, unique = true)
	private String login;
	@NotBlank @Size(min = 6)
	@Column(nullable = false)
	private String password;
	@CreationTimestamp @PastOrPresent
	@Column(name="creation_date", nullable = false)	
	private LocalDateTime creationDate;
	
	@Deprecated
	public User() {	}
	
	public User(String login,String password) {
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", creationDate=" + creationDate
				+ "]";
	}
}
