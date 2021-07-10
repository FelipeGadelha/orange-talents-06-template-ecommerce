package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.PurchaseRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event.PurchaseEvent;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.ProductRepository;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository.PurchaseRepository;

@RestController
@RequestMapping("/v1/product/{id}/purchase")
public class PurchaseController {
	
	private final ProductRepository productRepository;
	private final PurchaseRepository purchaseRepository;
	private final ApplicationEventPublisher publisher;
	
	@Autowired
	public PurchaseController(ProductRepository productRepository, 
			PurchaseRepository purchaseRepository,
			ApplicationEventPublisher publisher) {
		this.productRepository = productRepository;
		this.purchaseRepository = purchaseRepository;
		this.publisher = publisher;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> purchase(@PathVariable Long id,
			@Valid @RequestBody PurchaseRq purchaseRq,
			@AuthenticationPrincipal User buyer,
			UriComponentsBuilder uriBuilder){
		
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Produto de id: " + id + " não encontrado"));
		if (!product.subtractBySale(purchaseRq.getQuantity())) 
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "O vendedor não possui essa quantidade no estoque");

		var saved = purchaseRepository.save(purchaseRq.convert(buyer, product));
		String url = saved.getGatewayUrl(uriBuilder);
		
		publisher.publishEvent(new PurchaseEvent(saved));
		return ResponseEntity.status(HttpStatus.FOUND).body(url);
	}
	
	
	
}
