package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

@Service
public class NotifierBuyerByEmail implements NotifierBuyer {
	
	private static final String sendBy = "enviado por: meli@contato.com.br";

	@Override
	public void newSuccessfulTransaction(Purchase purchase) {
		System.err.println(sendBy);
		System.err.println("para: " + purchase.getBuyerName());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Pagamento foi efetuado com sucesso");
		System.err.println("corpo: " + System.lineSeparator()
				+ "O seu pagamento foi efetuado com sucesso" + System.lineSeparator()
				+ "data e hora: " + LocalDateTime.now() + System.lineSeparator()
				+ "vandedor: " + purchase.getSellerName() + System.lineSeparator()
				+ "produto: " + purchase.getProductName() + System.lineSeparator()
				+ "quantidade: " + purchase.getQuantity() + System.lineSeparator()
				+ "serviço: " + purchase.getGateway() + System.lineSeparator()
				);
		
	}

	@Override
	public void newfailTransaction(Purchase purchase) {
		System.err.println("para: " + purchase.getBuyerName());
		System.err.println("data: " + LocalDateTime.now());
		System.err.println("assunto: Falha ao processar pagamento");
		System.err.println("corpo: " + System.lineSeparator()
				+ "Falha ao processar o seu pagamento para" + System.lineSeparator()
				+ "produto: " + purchase.getProductName() + System.lineSeparator()
				+ "data e hora: " + LocalDateTime.now() + System.lineSeparator()
				+ "vendedor: " + purchase.getSellerName() + System.lineSeparator()
				+ "serviço: " + purchase.getGateway() + System.lineSeparator()
				);
		
	}
}
