package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public interface NotifierSeller {
	
	void newASk(Ask ask);
	void newPurchaser(Purchase purchase);
}
