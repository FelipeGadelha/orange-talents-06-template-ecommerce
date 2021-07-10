package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();
	
	@Deprecated
	public User() {	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	public User(String login, String password, List<Profile> profiles) {
		this.login = login;
		this.password = password;
		this.profiles = profiles;
		
	}

	public Long getId() {
		return id;
	}
//
//	public String getLogin() {
//		return login;
//	}

	public String getPassword() {
		return password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public List<Profile> getProfiles() {
		return profiles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", creationDate=" + creationDate
				+ ", profiles=" + profiles + "]";
	}
}
