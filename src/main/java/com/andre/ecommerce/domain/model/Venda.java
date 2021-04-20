package com.andre.ecommerce.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

	private static final short PARCELA_UNICA = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private FormaPagamento formaPagamento;

	private Short numeroParcelas;

	@ManyToOne
	@JoinColumn(name = "transportadora_id")
	private Transportadora transportadora;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVenda> itensCarrinho = new ArrayList<ItemVenda>();

	private BigDecimal valorFrete;

	private BigDecimal valorTotalItens;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusVenda status;

	public Venda() {
		this.status = StatusVenda.EM_ANDAMENTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Short getNumeroParcelas() {
		return numeroParcelas;
	}
	
	public StatusVenda getStatus() {
		return status;
	}

	public void pagarDebito() {
		this.formaPagamento = FormaPagamento.DEBITO;
		this.numeroParcelas = PARCELA_UNICA;
	}

	public void pagarBoleto() {
		this.formaPagamento = FormaPagamento.BOLETO;
		this.numeroParcelas = PARCELA_UNICA;
	}

	public void pagarCredito(Short numeroParcelas) {
		this.formaPagamento = FormaPagamento.CREDITO;
		this.numeroParcelas = numeroParcelas;
	}

	public void setNumeroParcelas(Short numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<ItemVenda> getItensCarrinho() {
		return itensCarrinho;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorTotalItens() {
		return valorTotalItens;
	}

	public void setValorTotalItens(BigDecimal valorTotalItens) {
		this.valorTotalItens = valorTotalItens;
	}
	
	public void finalizarVenda() {
		this.status = StatusVenda.FINALIZADA;
	}
	
	public void cancelarVenda() {
		this.status = StatusVenda.CANCELADA;
	}

}
