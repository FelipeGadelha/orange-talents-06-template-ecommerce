package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.NotNull;

public class InvoiceRq {
	
	@NotNull
	private Long purchaseId;
	@NotNull
	private Long buyerId;
	
	public InvoiceRq(Long purchaseId, Long buyerId) {
		this.purchaseId = purchaseId;
		this.buyerId = buyerId;
	}
	public Long getPurchaseId() {
		return purchaseId;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	@Override
	public String toString() {
		return "InvoiceRq [purchaseId=" + purchaseId + ", buyerId=" + buyerId + "]";
	}
}
