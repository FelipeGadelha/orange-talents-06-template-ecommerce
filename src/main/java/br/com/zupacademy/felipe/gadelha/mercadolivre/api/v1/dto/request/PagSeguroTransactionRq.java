package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayStatus;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Transaction;

public class PagSeguroTransactionRq {

	@NotBlank
	@NotNull
	private String id;
	@NotNull
	private GatewayStatus status;
	
	public PagSeguroTransactionRq(@NotNull String id, @NotNull GatewayStatus status) {
		this.id = id;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public GatewayStatus getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "TransactionRq [id=" + id + ", status=" + status + "]";
	}
	public Transaction convert(Purchase purchase) {
		return new Transaction(id, status, purchase);
	}
}
