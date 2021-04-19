package com.andre.ecommerce.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.andre.ecommerce.domain.model.TipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpfCnpj;
	
	@NotNull
	private TipoPessoa tipoPessoa;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String email;
	
	@NotNull
	private EnderecoInput endereco;

}
