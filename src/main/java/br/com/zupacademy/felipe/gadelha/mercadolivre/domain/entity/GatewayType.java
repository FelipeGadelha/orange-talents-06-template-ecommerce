package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayType {

	PAYPAL {
		@Override
		public String getUri(Purchase purchase, UriComponentsBuilder uriBuilder) {
			var uri = uriBuilder
					.path("/v1/process-pay/paypal/{id}")
					.buildAndExpand(purchase.getId())
					.toString();
			return "paypal.com?buyerId=" 
					+ purchase.getId() 
					+ "&redirectUrl=" + uri;
		}
	},
	PAGSEGURO {
	@Override
		public String getUri(Purchase purchase, UriComponentsBuilder uriBuilder) {
			var uri = uriBuilder
					.path("/v1/process-pay/pagseguro/{id}")
					.buildAndExpand(purchase.getId())
					.toString();
			return "pagseguro.com?returnId=" 
					+ purchase.getId() 
					+ "&redirectUrl=" + uri;
		}
	};
	public abstract String getUri(Purchase purchase, UriComponentsBuilder uriBuilder);
}
