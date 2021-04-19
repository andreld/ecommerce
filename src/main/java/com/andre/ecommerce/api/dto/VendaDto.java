package com.andre.ecommerce.api.dto;

import java.util.List;

import com.andre.ecommerce.domain.model.Cliente;
import com.andre.ecommerce.domain.model.FormaPagamento;
import com.andre.ecommerce.domain.model.ItemVenda;
import com.andre.ecommerce.domain.model.Loja;
import com.andre.ecommerce.domain.model.StatusVenda;
import com.andre.ecommerce.domain.model.Transportadora;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDto {

	private Long id;

	private FormaPagamento formaPagamento;

	private Transportadora transportadora;

	private Cliente cliente;
	
	private Loja loja;

	private List<ItemVendaDto> itensCarrinho;

	private StatusVenda status;
	
}
