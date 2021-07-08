package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import org.springframework.stereotype.Service;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

@Service
public class NotifierByEmail implements Notifier {
	
	@Override
	public void notify(User user, String message) {
		System.out.printf("Notificando %s: " + 
				System.lineSeparator()
				+"%s",
				user.getUsername(), message);
	}
	
	

}
