package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.InvoiceRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

@Component
public class ProcessPayInvoice {

	private final RestTemplate restTemplate = new RestTemplate();
	
	public void process(Purchase purchase) {
		var invoiceRq = new InvoiceRq(purchase.getId(), purchase.getBuyer().getId());
		restTemplate.postForEntity("http://localhost:8080/invoice", invoiceRq, String.class);
	}

}
