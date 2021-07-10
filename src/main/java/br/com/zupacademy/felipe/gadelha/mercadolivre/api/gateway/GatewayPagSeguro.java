package br.com.zupacademy.felipe.gadelha.mercadolivre.api.gateway;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public class GatewayPagSeguro implements GatewayPayment {
	
	private Purchase purchase;

	public GatewayPagSeguro(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public void processPayment() {
		
	}

}
