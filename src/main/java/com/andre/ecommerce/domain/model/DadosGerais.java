package com.andre.ecommerce.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class DadosGerais {
	private String nome;

	private String cpfCnpj;

	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa;

	private String telefone;

	private String email;

}
