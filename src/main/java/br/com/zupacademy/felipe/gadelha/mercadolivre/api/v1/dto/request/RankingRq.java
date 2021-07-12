package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.NotNull;

public class RankingRq {
	
	@NotNull
	private Long purchaseId;
	@NotNull
	private Long sellerId;
	
	public RankingRq(Long purchaseId, Long sellerId) {
		this.purchaseId = purchaseId;
		this.sellerId = sellerId;
	}
	public Long getPurchaseId() {
		return purchaseId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	@Override
	public String toString() {
		return "RankingRq [purchaseId=" + purchaseId + ", sellerId=" + sellerId + "]";
	}
	
}
