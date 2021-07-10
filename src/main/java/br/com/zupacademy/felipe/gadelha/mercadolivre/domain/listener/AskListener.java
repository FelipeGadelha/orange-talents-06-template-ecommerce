package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.Notifier;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event.AskEvent;

@Component
public class AskListener {
	
	private final Notifier notifier;
	
	@Autowired
	public AskListener(Notifier notifier) {
		this.notifier = notifier;
	}

	@EventListener
	public void asklistener(AskEvent event) {
		notifier.newASk(event.getAsk());
	}
}
