package br.com.zupacademy.felipe.gadelha.mercadolivre.api.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${meli.jwt.expiration}")
	private String expiration;
	
	@Value("${meli.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		var now = new Date();
//		var expirationDate = new Date(now.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API MELI - Mercado Livre")
				.setSubject(logged.getId().toString())
				.setIssuedAt(now)
				.setExpiration(this.expirationDate())// expirationDate
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	private Date expirationDate() {
		return Date.from(LocalDateTime.now()
				.plusHours(1)
				.atZone(ZoneId.of("America/Sao_Paulo"))
				.toInstant());
	}
	
	public boolean isValidToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public Long getUserId(String token) {
		var claims = Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}
}
