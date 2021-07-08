package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
