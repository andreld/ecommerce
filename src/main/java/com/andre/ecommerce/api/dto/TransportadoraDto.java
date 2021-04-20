package com.andre.ecommerce.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportadoraDto {
	
	private long id;

	private DadosGeraisDto dadosGerais;
	
	private BigDecimal valorFrete;
	
}
