package com.andre.ecommerce.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andre.ecommerce.api.dto.VendaDto;
import com.andre.ecommerce.domain.exception.NegocioException;
import com.andre.ecommerce.domain.model.ItemVenda;
import com.andre.ecommerce.domain.model.Venda;
import com.andre.ecommerce.domain.repository.ClienteRepository;
import com.andre.ecommerce.domain.repository.EstoqueProdutoRepository;
import com.andre.ecommerce.domain.repository.LojaRepository;
import com.andre.ecommerce.domain.repository.TransportadoraRepository;
import com.andre.ecommerce.domain.repository.VendaRepository;

@Service
public class GestaoVendaService {

	private static final long LOJA_ID = 2L;

	@Autowired
	VendaRepository vendaRepository;

	@Autowired
	EstoqueProdutoRepository estoqueProdutoRepository;

	@Autowired
	TransportadoraRepository transportadoraRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	LojaRepository lojaRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public VendaDto salvar(Venda venda, long clienteId) {

		venda.setCliente(
				clienteRepository.findById(clienteId).orElseThrow(() -> new NegocioException("Cliente não existe")));
		venda.setTransportadora(transportadoraRepository.findById(venda.getTransportadora().getId())
				.orElseThrow(() -> new NegocioException("Transportadora não existe")));
		venda.setLoja(lojaRepository.findById(LOJA_ID).orElseThrow(() -> new NegocioException("Erro interno")));

		for (ItemVenda item : venda.getItensCarrinho()) {
			item.setVenda(venda);
			item.setEstoqueProduto(estoqueProdutoRepository.findById(item.getEstoqueProduto().getId())
					.orElseThrow(() -> new NegocioException("Produto não existe")));
		}
		
		venda.setValorTotalItens(calcularValorTotalItens(venda));
		venda.finalizarVenda();
		venda.setValorFrete(new BigDecimal("10.00"));
		Venda vendaRetorno = vendaRepository.save(venda);

		return toDto(vendaRetorno);
	}

	public List<VendaDto> listar() {
		List<Venda> listaVendas = vendaRepository.findAll();

		return toDtoList(listaVendas);
	}

	public VendaDto buscarPorId(long id) {

		Venda vendaRetorno = vendaRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Compra não encontrada."));

		return toDto(vendaRetorno);
	}

	public BigDecimal calcularValorTotalItens(Venda venda) {

		detalhaItensCarrinho(venda);

		return venda.getItensCarrinho().stream().map(
				item -> (BigDecimal) item.getEstoqueProduto().getValor().multiply(new BigDecimal(item.getQuantidade())))
				.reduce((atual, proximo) -> atual.add(proximo)).orElse(BigDecimal.ZERO.setScale(2));
	}

	public void detalhaItensCarrinho(Venda venda) {
		venda.getItensCarrinho().forEach(item -> {
			item.setEstoqueProduto(estoqueProdutoRepository.findById(item.getEstoqueProduto().getId())
					.orElseThrow(() -> new NegocioException("Produto não existe")));
			item.setId(null);
		});
	}

	// public VendaDto salvar

//	@Transactional
//	public VendaDto atualizar(Long vendaId, Venda venda) {
//		
//		if (!vendaRepository.existsById(vendaId)) {
//			throw new NegocioException(PRODUTO_NAO_EXISTE);
//		}
//		
//		venda.setId(vendaId);
//		Venda vendaRetorno = vendaRepository.save(venda);
//
//		return toDto(vendaRetorno);
//	}

//	@Transactional
//	public void excluir(Long id) {
//		vendaRepository.deleteById(id);
//	}

	private VendaDto toDto(Venda venda) {
		return modelMapper.map(venda, VendaDto.class);
	}

	private List<VendaDto> toDtoList(List<Venda> listaVenda) {
		return listaVenda.stream().map(venda -> toDto(venda)).collect(Collectors.toList());
	}

}
