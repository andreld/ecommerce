package com.andre.ecommerce.api.dto;

import java.math.BigDecimal;
import java.util.List;

import com.andre.ecommerce.domain.model.FormaPagamento;
import com.andre.ecommerce.domain.model.StatusVenda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDto {

	private long id;

	private FormaPagamento formaPagamento;

	private TransportadoraDto transportadora;

	private ClienteDto cliente;
	
	private LojaDto loja;

	private List<ItemVendaDto> itensCarrinho;

	private StatusVenda status;
	
	private BigDecimal valorTotalItens;
	
	private BigDecimal valorFrete;
	
	public BigDecimal getValorTotal() {
		return valorTotalItens.add(valorFrete);
	}
	
}
