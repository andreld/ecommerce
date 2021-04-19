package com.andre.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.EstoqueProduto;

@Repository
public interface EstoqueProdutoRepository extends JpaRepository<EstoqueProduto, Long> {
	
	List<EstoqueProduto> findByDescricao(String descricao);
	EstoqueProduto findByCodigoBarras(String codigoBarras);
	List<EstoqueProduto> findByDescricaoContainingIgnoreCase(String descricao);
	boolean existsByCodigoBarras(String codigoBarras);
}
