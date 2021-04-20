package com.andre.ecommerce.api.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoInput {

	@NotNull
	@Valid
	private List<ItemVendaInput> itensCarrinho;

}
