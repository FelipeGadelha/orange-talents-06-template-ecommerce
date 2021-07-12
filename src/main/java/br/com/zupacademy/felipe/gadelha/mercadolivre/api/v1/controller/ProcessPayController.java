package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.PagSeguroTransactionRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.PaypalTransactionRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayStatus;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event.ProcessPayEvent;

@RestController
@RequestMapping("/v1/process-pay")
public class ProcessPayController {

	private EntityManager manager;
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	public ProcessPayController(EntityManager manager, ApplicationEventPublisher eventPublisher) {
		this.manager = manager;
		this.eventPublisher = eventPublisher;
	}

	@PostMapping("/pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> processPagseguro(@PathVariable Long id, @Valid PagSeguroTransactionRq transactionRq) {
		var purchase = Optional.ofNullable(manager.find(Purchase.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra de id: " + id + " não encontrado"));
		var transaction = transactionRq.convert(purchase);
		purchase.addTransaction(transaction);
		manager.merge(purchase);
		newEvent(purchase, transaction.getStatus());
		return ResponseEntity.ok(purchase);
	}
	@PostMapping("/paypal/{id}")
	@Transactional
	public ResponseEntity<?> processPaypal(@PathVariable Long id, @Valid PaypalTransactionRq transactionRq) {
		var purchase = Optional.ofNullable(manager.find(Purchase.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Compra de id: " + id + " não encontrado"));
		var transaction = transactionRq.convert(purchase);
		purchase.addTransaction(transaction);
		manager.merge(purchase);
		newEvent(purchase, transaction.getStatus());
		return ResponseEntity.ok(purchase);
	}
	
	private void newEvent(Purchase purchase, GatewayStatus statusPay) {
		this.eventPublisher.publishEvent(new ProcessPayEvent(purchase, statusPay));
	}
	

}
