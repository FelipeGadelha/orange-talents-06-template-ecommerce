package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public interface Notifier {
	
	void newASk(Ask ask);
	void newPurchaser(Purchase purchase);
	
}
