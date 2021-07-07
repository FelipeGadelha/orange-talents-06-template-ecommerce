package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.ProductRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.ProductRepository;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	private final ProductRepository productRepository;
	
	private final EntityManager manager;
	
	public ProductController(ProductRepository productRepository, EntityManager manager) {
		this.productRepository = productRepository;
		this.manager = manager;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody ProductRq productRq,
				@AuthenticationPrincipal User user) {
		System.err.println(user.toString());
		productRepository.save(productRq.convert(manager, user));
		return ResponseEntity.ok().build();
	}
}
