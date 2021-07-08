package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.ImageRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.ProductRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response.ProductDetailsRs;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.UploadFake;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.Uploader;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.ProductRepository;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	private final ProductRepository productRepository;
	
	private final EntityManager manager;
	
	private final Uploader uploadFake;
	
	@Autowired
	public ProductController(ProductRepository productRepository, EntityManager manager, UploadFake uploadFake) {
		this.productRepository = productRepository;
		this.manager = manager;
		this.uploadFake = uploadFake;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody ProductRq productRq,
				@AuthenticationPrincipal User user) {
		productRepository.save(productRq.convert(manager, user));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/images")
	@Transactional
	public ResponseEntity<?> updateImage(@PathVariable Long id,
			@Valid ImageRq imageRq, 
			@AuthenticationPrincipal User user){
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(!product.belongsToUser(user))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		
		Set<String> links = uploadFake.send(imageRq.getImages());
		product.setImages(links);
		productRepository.save(product);
		return ResponseEntity.ok().build();
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> details(@PathVariable Long id) {
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(new ProductDetailsRs(product));
	}
}
