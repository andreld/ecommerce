package com.andre.ecommerce.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.andre.ecommerce.domain.exception.NegocioException;

@Entity
public class EstoqueProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigoBarras;

	private String descricao;

	private BigDecimal valor;

	private int quantidade;
	
	@OneToMany(mappedBy = "estoqueProduto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVenda> itensCarrinho = new ArrayList<ItemVenda>(); 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade >= 0) {
			this.quantidade = quantidade;
		} else {
			throw new NegocioException("Quantidade não pode ser menor que zero");
		}
	}

	public int adicionar(int quantidadeAdicionada) {
		this.quantidade += quantidadeAdicionada;
		return this.quantidade;
	}

	public int subtrair(int quantidadeRemovida) {
		if (quantidadeRemovida > this.quantidade) {
			throw new NegocioException("A quantidade solicitada é maior que o total atual no estoque.");
		}
		this.quantidade -= quantidadeRemovida;
		return this.quantidade;
	}

	public List<ItemVenda> getItensCarrinho() {
		return itensCarrinho;
	}

}
