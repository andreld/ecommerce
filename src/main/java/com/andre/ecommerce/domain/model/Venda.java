package com.andre.ecommerce.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Pagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "transportadora_id")
	private Pessoa transportadora;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Pessoa cliente;

	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Pessoa loja;

	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itensCarrinho;

	@Enumerated(EnumType.STRING)
	private StatusVenda status;

	public Venda() {

	}

	public Venda(Pessoa cliente) {
		this.cliente = cliente;
		this.status = StatusVenda.EM_ANDAMENTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Pagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Pessoa getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Pessoa transportadora) {
		this.transportadora = transportadora;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Pessoa getLoja() {
		return loja;
	}

	public List<ItemVenda> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(List<ItemVenda> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

}
