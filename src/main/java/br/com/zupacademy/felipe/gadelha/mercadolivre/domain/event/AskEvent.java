package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;

public class AskEvent {
	
	private Ask ask;
	
	public AskEvent(Ask ask) {
		this.ask = ask;
	}
	public Ask getAsk() {
		return ask;
	}
}
