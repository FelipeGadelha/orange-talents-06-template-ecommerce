package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
