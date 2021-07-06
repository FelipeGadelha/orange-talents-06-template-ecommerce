package br.com.zupacademy.felipe.gadelha.mercadolivre.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.UserRepository;

public class TokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository repository;

	public TokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = retrieveToken(request);
		boolean valido = tokenService.isValidToken(token);
		if (valido) authenticate(token);
		filterChain.doFilter(request, response);
	}
	private void authenticate(String token) {
		Long userId = tokenService.getUserId(token);
		var user = repository.findById(userId).get();
		var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}
}
