package com.andre.ecommerce.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaDto {
	
	private EstoqueProdutoDto estoqueProduto;

	private Long quantidade;
		
	public BigDecimal getValorTotalItem() {
		return getEstoqueProduto().getValor().multiply(new BigDecimal(quantidade));
	}
}
