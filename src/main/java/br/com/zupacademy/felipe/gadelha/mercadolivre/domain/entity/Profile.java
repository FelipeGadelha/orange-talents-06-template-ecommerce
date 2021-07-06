package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "profiles")
public class Profile implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@Deprecated
	public Profile() {	}
	
	public Profile(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String getAuthority() {
		return name;
	}

}
