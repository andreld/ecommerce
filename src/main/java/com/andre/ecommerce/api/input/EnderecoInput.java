package com.andre.ecommerce.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	private long id;
	
	@NotBlank
	private String cep;
	
	@NotNull
	private int numero;

	@NotBlank
	private String rua;
	
	@NotBlank
	private String bairro;
	
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String estado;
}
