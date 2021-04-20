package com.andre.ecommerce.api.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.andre.ecommerce.domain.model.FormaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaInput {
	
	@NotNull
	private FormaPagamento formaPagamento;
	
	@NotNull
	private Short numeroParcelas;
	
	@NotNull
	@Valid
	private Long transportadoraId;
	
	@NotNull
	@Valid
	private List<ItemVendaInput> itensCarrinho = new ArrayList<ItemVendaInput>();
	
}
