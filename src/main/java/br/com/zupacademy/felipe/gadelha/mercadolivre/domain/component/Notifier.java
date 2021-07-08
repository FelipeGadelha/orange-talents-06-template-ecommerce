package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

public interface Notifier {
	
	public void notify(User user, String message);

}
