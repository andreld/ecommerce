package com.andre.ecommerce.api.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaInput {
	
	@NotNull
	private long estoqueProdutoId;
	
	@NotNull
	private int quantidade;
	
}
