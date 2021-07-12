package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.InvoiceRq;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@PostMapping
	public ResponseEntity<?> process(@Valid @RequestBody InvoiceRq invoiceRq) {
		System.out.println(invoiceRq);
		return ResponseEntity.ok().build();
	}
}
