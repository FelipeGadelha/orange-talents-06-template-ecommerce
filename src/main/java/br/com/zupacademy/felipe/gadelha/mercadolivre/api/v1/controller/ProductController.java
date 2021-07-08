package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.component.UploadFake;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.component.Uploader;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.ImageRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.OpinionRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.ProductRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;
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
		System.err.println(user.toString());
		productRepository.save(productRq.convert(manager, user));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/images")
	@Transactional
	public ResponseEntity<?> updateImage(@PathVariable Long id,
			@Valid ImageRq imageRq, 
			@AuthenticationPrincipal User user){
		Optional<Product> optional = productRepository.findById(id);
		
		if(!optional.isPresent()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		var product = optional.get();
		if(!product.belongsToUser(user))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		
		Set<String> links = uploadFake.send(imageRq.getImages());
		product.setImages(links);
		productRepository.save(product);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/{id}/opinions")
	@Transactional
	public ResponseEntity<?> createOpinion(@PathVariable Long id,
			@RequestBody @Valid OpinionRq opinionRq, 
			@AuthenticationPrincipal User user){
		Optional<Product> optional = productRepository.findById(id);
		if(!optional.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		var product = optional.get();
		var opinion = opinionRq.convert(user, product);
		manager.persist(opinion);
		return ResponseEntity.ok().build();
	}
	
}
