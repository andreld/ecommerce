package com.andre.ecommerce.api.dto;

import com.andre.ecommerce.domain.model.TipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
	
	private Long id;
	private String nome;
	private String cpfCnpj;
	private TipoPessoa tipoPessoa;
	private String telefone;
	private String email;
	private EnderecoDto endereco;

}
