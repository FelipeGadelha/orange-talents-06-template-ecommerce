package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayType;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public class PurchaseRq {
	
	@NotNull
	private GatewayType gateway;
	@NotNull
	@Positive
	private Integer quantity;
	@NotNull
	@Positive
	private BigDecimal productValue;
	
	public GatewayType getGateway() {
		return gateway;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public BigDecimal getProductValue() {
		return productValue;
	}
	
	public Purchase convert(User buyer, Product product) {
		return new Purchase(buyer, product, 
				quantity, 
				productValue,
				gateway);
	}
}
