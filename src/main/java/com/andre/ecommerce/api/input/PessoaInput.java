package com.andre.ecommerce.api.input;

import com.andre.ecommerce.domain.model.TipoPessoa;
import com.andre.ecommerce.domain.model.TipoUsuario;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class PessoaInput {
	private String nome;

	private String cpfCnpj;

	private TipoPessoa tipoPessoa;

	private TipoUsuario tipoUsuario;

	private EnderecoInput endereco;

}
