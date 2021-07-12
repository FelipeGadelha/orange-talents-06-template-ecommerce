package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public interface NotifierBuyer {
	
	void newSuccessfulTransaction(Purchase purchase);
	void newfailTransaction(Purchase purchase);

}
