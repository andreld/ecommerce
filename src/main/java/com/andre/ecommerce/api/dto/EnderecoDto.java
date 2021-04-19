package com.andre.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
	
	private String cep;
	private int numero;
	private String rua;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	
}
