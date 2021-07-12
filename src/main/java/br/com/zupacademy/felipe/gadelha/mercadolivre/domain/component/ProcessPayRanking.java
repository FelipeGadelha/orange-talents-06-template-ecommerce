package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.RankingRq;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

@Component
public class ProcessPayRanking {

	private final RestTemplate restTemplate = new RestTemplate();
	
	public void process(Purchase purchase) {
		var rankingRq = new RankingRq(purchase.getId(), purchase.getSellerId());
		restTemplate.postForEntity("http://localhost:8080/ranking", rankingRq, String.class);
	}

}
