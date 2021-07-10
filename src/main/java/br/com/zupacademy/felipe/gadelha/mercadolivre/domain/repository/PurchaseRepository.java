package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
