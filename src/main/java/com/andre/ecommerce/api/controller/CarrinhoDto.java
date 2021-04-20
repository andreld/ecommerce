package com.andre.ecommerce.api.controller;

import java.math.BigDecimal;
import java.util.List;

import com.andre.ecommerce.api.dto.ItemVendaDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoDto {
	private List<ItemVendaDto> itensCarrinho;
	
	private BigDecimal valorTotalItens;
}
