package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.event.AskEvent;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.AskRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.ProductRepository;

@RestController
@RequestMapping("/v1/products/{id}/ask")
public class AskController {

	private final ProductRepository productRepository;
	private final EntityManager manager;
	private final ApplicationEventPublisher publisher;
	
	@Autowired
	public AskController(
			ProductRepository productRepository, 
			EntityManager manager, 
			ApplicationEventPublisher publisher) {
		
		this.productRepository = productRepository;
		this.manager = manager;
		this.publisher = publisher;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createOpinion(@PathVariable Long id,
			@RequestBody @Valid AskRq askRq, 
			@AuthenticationPrincipal User user){
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Ask ask = new Ask(askRq.getTitle(), user, product);
		manager.persist(ask);
		
		publisher.publishEvent(new AskEvent(ask));
		return ResponseEntity.ok().build();
	}
}
