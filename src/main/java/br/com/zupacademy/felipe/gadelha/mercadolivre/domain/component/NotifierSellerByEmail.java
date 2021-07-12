package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

@Service
public class NotifierSellerByEmail implements NotifierSeller {
	
	private static final String sendBy = "enviado por: meli@contato.com.br";
	
	@Override
	public void newASk(Ask ask) {
		System.err.println(sendBy);
		System.err.println("para: " + ask.getSellerName());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Uma nova pergunta sobre o seu produto " + ask.getProductName());
		System.err.println("corpo: " + System.lineSeparator()
				+ "Você recebeu uma pergunta referente ao anúncio do seu produto no mercado livre" + System.lineSeparator()
				+ "pergunta: " + ask.getTitle() + System.lineSeparator()
				+ "produto: " + ask.getProductName() + System.lineSeparator()
				+ "descrição: " + ask.getProductDescription() + System.lineSeparator()
				+ "data e hora: " + ask.getCreationDate() + System.lineSeparator()
				+ "usuário: " + ask.getAskUsername() + System.lineSeparator()
				);
	}

	@Override
	public void newPurchaser(Purchase purchase) {
		System.err.println(sendBy);
		System.err.println("para: " + purchase.getSellerName());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Nova compra efetuada para o produto anúnciado");
		System.err.println("corpo: " + System.lineSeparator()
				+ "Você recebeu uma nova compra efetuada no anúncio do seu produto no mercado livre" + System.lineSeparator()
				+ "produto: " + purchase.getProductName() + System.lineSeparator()
				+ "data e hora: " + LocalDateTime.now() + System.lineSeparator()
				+ "comprador: " + purchase.getBuyerName() + System.lineSeparator()
				);
	}
}
