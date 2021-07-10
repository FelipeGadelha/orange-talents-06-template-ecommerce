package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public class PurchaseEvent {

	private Purchase purchase;

	public PurchaseEvent(Purchase purchase) {
		this.purchase = purchase;
	}
	public Purchase getPurchase() {
		return purchase;
	}
}
