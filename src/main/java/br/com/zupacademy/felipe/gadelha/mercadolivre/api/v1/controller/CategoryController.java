package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.CategoryRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.CategoryRepository;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

	private final CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CategoryRq categoryRq) {
		var category = categoryRq.convert(categoryRepository);
		categoryRepository.save(category);
		return ResponseEntity.ok().build();
	}
}
