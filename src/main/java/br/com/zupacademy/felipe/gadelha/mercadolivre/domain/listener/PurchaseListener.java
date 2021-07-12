package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.NotifierSeller;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event.PurchaseEvent;

@Component
public class PurchaseListener {
	
	private final NotifierSeller notifier;
	
	@Autowired
	public PurchaseListener(NotifierSeller notifier) {
		this.notifier = notifier;
	}
	
	@EventListener
	public void notifierPurchaseListener(PurchaseEvent event) {
		notifier.newPurchaser(event.getPurchase());
	}
}
