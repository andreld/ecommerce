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
import com.andre.ecommerce.domain.model.Cliente;
import com.andre.ecommerce.domain.model.EstoqueProduto;
import com.andre.ecommerce.domain.model.ItemVenda;
import com.andre.ecommerce.domain.model.Transportadora;
import com.andre.ecommerce.domain.model.Venda;
import com.andre.ecommerce.domain.repository.ClienteRepository;
import com.andre.ecommerce.domain.repository.EstoqueProdutoRepository;
import com.andre.ecommerce.domain.repository.LojaRepository;
import com.andre.ecommerce.domain.repository.TransportadoraRepository;
import com.andre.ecommerce.domain.repository.VendaRepository;

@Service
public class GestaoVendaService {

	private static final long LOJA_ID = 1L;

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
		
		Venda resumoVenda = resumoVenda(venda, clienteId);
		resumoVenda.finalizarVenda();

		return toDto(vendaRepository.save(resumoVenda));
	}

	private Venda resumoVenda(Venda venda, long clienteId) {
		
		venda.setCliente(clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não existe")));
		Transportadora transportadora = transportadoraRepository.findById(venda.getTransportadora().getId())
				.orElseThrow(() -> new NegocioException("Transportadora não existe"));
		venda.setValorFrete(transportadora.getValorFrete());
		venda.setTransportadora(transportadora);
		venda.setLoja(lojaRepository.findById(LOJA_ID)
				.orElseThrow(() -> new NegocioException("Erro interno")));

		for (ItemVenda item : venda.getItensCarrinho()) {
			item.setVenda(venda);
			EstoqueProduto estoque = estoqueProdutoRepository.findById(item.getEstoqueProduto().getId())
					.orElseThrow(() -> new NegocioException("Produto não existe"));
			estoque.subtrair(item.getQuantidade());
			item.setEstoqueProduto(estoque);
		}
		venda.setValorTotalItens(calcularValorTotalItens(venda));

		
		return venda;
	}
	
	public VendaDto checkout(Venda venda, long clienteId) {
		return toDto(resumoVenda(venda, clienteId));
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

	private BigDecimal calcularValorTotalItens(Venda venda) {

		return venda.getItensCarrinho().stream().map(
				item -> (BigDecimal) item.getEstoqueProduto().getValor().multiply(new BigDecimal(item.getQuantidade())))
				.reduce((atual, proximo) -> atual.add(proximo)).orElse(BigDecimal.ZERO.setScale(2));
	}

	private VendaDto toDto(Venda venda) {
		return modelMapper.map(venda, VendaDto.class);
	}

	private List<VendaDto> toDtoList(List<Venda> listaVenda) {
		return listaVenda.stream().map(venda -> toDto(venda)).collect(Collectors.toList());
	}

	public List<VendaDto> listar(Long clienteId) {
		
		Cliente cliente = clienteRepository.findById(clienteId)
		.orElseThrow(() -> new NegocioException("Cliente não existe"));
		
		return toDtoList(vendaRepository.findByCliente(cliente));

	}

	public VendaDto calcularSubTotalETotal(Venda venda) {
		for (ItemVenda item : venda.getItensCarrinho()) {

			EstoqueProduto estoque = estoqueProdutoRepository.findById(item.getEstoqueProduto().getId())
					.orElseThrow(() -> new NegocioException("Produto não existe"));
			item.setEstoqueProduto(estoque);
		}
		venda.setValorTotalItens(calcularValorTotalItens(venda));
		return toDto(venda);
	}

}
