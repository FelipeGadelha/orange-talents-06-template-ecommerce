package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request.RankingRq;

@RestController
@RequestMapping("/ranking")
public class RankingController {

	@PostMapping
	public ResponseEntity<?> process(@Valid @RequestBody RankingRq rankingRq) {
		System.out.println(rankingRq);
		return ResponseEntity.ok().build();
	}
}
