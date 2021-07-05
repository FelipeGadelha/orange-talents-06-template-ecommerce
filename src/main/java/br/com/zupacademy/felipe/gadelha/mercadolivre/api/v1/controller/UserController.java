package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.UserRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.UserRepository;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserRq userRq) {
		var user = userRq.convert();
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
}
