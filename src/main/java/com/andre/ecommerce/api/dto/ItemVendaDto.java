package com.andre.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaDto {
	private Long id;
	
	private EstoqueProdutoDto estoqueProduto;

	private Long quantidade;
}
