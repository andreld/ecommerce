package com.andre.ecommerce.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueProdutoDto {
	
	private Long id;
	private String codigoBarras;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	
}
