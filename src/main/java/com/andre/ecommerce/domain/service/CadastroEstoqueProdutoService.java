package com.andre.ecommerce.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andre.ecommerce.api.dto.EstoqueProdutoDto;
import com.andre.ecommerce.domain.exception.NegocioException;
import com.andre.ecommerce.domain.model.EstoqueProduto;
import com.andre.ecommerce.domain.repository.EstoqueProdutoRepository;

@Service
public class CadastroEstoqueProdutoService {

	private static final String PRODUTO_NAO_EXISTE = "Produto não existe.";

	@Autowired
	EstoqueProdutoRepository estoqueProdutoRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public EstoqueProdutoDto salvar(EstoqueProduto estoqueProduto) {

		existePorCodigoBarras(estoqueProduto);

		EstoqueProduto estoqueProdutoRetorno = estoqueProdutoRepository.save(estoqueProduto);

		return toDto(estoqueProdutoRetorno);
	}

	public void existePorCodigoBarras(EstoqueProduto estoqueProduto) {
		if (estoqueProdutoRepository.existsByCodigoBarras(estoqueProduto.getCodigoBarras())) {
			throw new NegocioException("Produto já cadastrado com este código de barras");
		}
	}

	public List<EstoqueProdutoDto> listar() {
		List<EstoqueProduto> listaEstoqueProdutos = estoqueProdutoRepository.findAll();

		return toDtoList(listaEstoqueProdutos);
	}

	public EstoqueProdutoDto buscarPorId(Long id) {

		EstoqueProduto estoqueProdutoRetorno = estoqueProdutoRepository.findById(id)
				.orElseThrow(() -> new NegocioException(PRODUTO_NAO_EXISTE));

		return toDto(estoqueProdutoRetorno);
	}
	
	@Transactional
	public EstoqueProdutoDto atualizar(Long estoqueProdutoId, EstoqueProduto estoqueProduto) {
		
		existePorId(estoqueProdutoId);
		
		estoqueProduto.setId(estoqueProdutoId);
		EstoqueProduto estoqueProdutoRetorno = estoqueProdutoRepository.save(estoqueProduto);

		return toDto(estoqueProdutoRetorno);
	}

	public void existePorId(Long estoqueProdutoId) {
		if (!estoqueProdutoRepository.existsById(estoqueProdutoId)) {
			throw new NegocioException(PRODUTO_NAO_EXISTE);
		}
	}
	
	@Transactional
	public void excluir(Long id) {
		estoqueProdutoRepository.deleteById(id);
	}

	private EstoqueProdutoDto toDto(EstoqueProduto estoqueProduto) {
		return modelMapper.map(estoqueProduto, EstoqueProdutoDto.class);
	}

	private List<EstoqueProdutoDto> toDtoList(List<EstoqueProduto> listaEstoqueProduto) {
		return listaEstoqueProduto.stream().map(estoqueProduto -> toDto(estoqueProduto)).collect(Collectors.toList());
	}

}
