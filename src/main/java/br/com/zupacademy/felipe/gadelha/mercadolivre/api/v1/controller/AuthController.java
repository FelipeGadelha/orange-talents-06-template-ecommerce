package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.security.TokenService;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.TokenRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response.TokenRs;

@RestController
@RequestMapping("/v1/auth")
//@Profile(value = {"prod", "test"})
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> authenticate(@RequestBody @Valid TokenRq tokenRq) {
		var loginData = tokenRq.convertToAuth();
		try {
			var authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenRs(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
