package com.andre.ecommerce.api.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueProdutoInput {
	
	@NotBlank
	private String codigoBarras;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private Integer quantidade;
	
}
