package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

@Service
public class NotifierSellerByEmail implements Notifier {
	
	@Override
	public void newASk(Ask ask) {
		System.err.println("enviado por: meli@contato.com.br");
		System.err.println("para: " + ask.getProduct().getOwner());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Uma nova pergunta sobre o seu produto " + ask.getProduct().getName());
		System.err.println("corpo: " + System.lineSeparator()
				+ "Você recebeu uma pergunta referente ao anúncio do seu produto no mercado livre" + System.lineSeparator()
				+ "pergunta: " + ask.getTitle() + System.lineSeparator()
				+ "produto: " + ask.getProduct() + System.lineSeparator()
				+ "data e hora: " + ask.getCreationDate() + System.lineSeparator()
				+ "user: " + ask.getUser().getUsername() + System.lineSeparator()
				);
	}

	@Override
	public void newPurchaser(Purchase purchase) {
		System.err.println("enviado por: meli@contato.com.br");
		System.err.println("para: " + purchase.getProduct().getOwner());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Nova compra efetuada para o produto anúnciado");
		System.err.println("corpo: " + System.lineSeparator()
				+ "Você recebeu uma nova compra efetuada no anúncio do seu produto no mercado livre" + System.lineSeparator()
				+ "produto: " + purchase.getProduct() + System.lineSeparator()
				+ "data e hora: " + LocalDateTime.now() + System.lineSeparator()
				+ "comprador: " + purchase.getBuyer().getUsername() + System.lineSeparator()
				);
	}
}
