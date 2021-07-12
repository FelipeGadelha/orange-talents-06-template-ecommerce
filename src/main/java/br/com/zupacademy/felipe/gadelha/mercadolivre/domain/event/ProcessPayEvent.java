package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayStatus;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public class ProcessPayEvent {

	private Purchase purchase;
	private GatewayStatus statusPay;

	public ProcessPayEvent(Purchase purchase, GatewayStatus statusPay) {
		this.purchase = purchase;
		this.statusPay = statusPay;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public GatewayStatus getStatusPay() {
		return statusPay;
	}
	
}
