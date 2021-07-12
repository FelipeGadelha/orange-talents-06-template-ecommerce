package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayStatus;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Transaction;

public class PaypalTransactionRq {

	@NotBlank
	@NotNull
	private String id;
	@NotNull
	@Range(min = 0, max = 1)
	private int status;
	
	public PaypalTransactionRq(@NotNull String id, @NotNull @Range(min = 0, max = 1) int status) {
		this.id = id;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "TransactionRq [id=" + id + ", status=" + status + "]";
	}
	public Transaction convert(Purchase purchase) {
		GatewayStatus statusPaypal = 
				this.status == 0 ? 
						GatewayStatus.ERROR : 
							GatewayStatus.SUCCESS;
		return new Transaction(id, statusPaypal, purchase);
	}
}
