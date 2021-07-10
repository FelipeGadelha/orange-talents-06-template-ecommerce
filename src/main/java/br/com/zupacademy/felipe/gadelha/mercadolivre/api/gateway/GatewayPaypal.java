package br.com.zupacademy.felipe.gadelha.mercadolivre.api.gateway;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public class GatewayPaypal implements GatewayPayment {

	private Purchase purchase;

	public GatewayPaypal(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public void processPayment() {
		
	}
}
