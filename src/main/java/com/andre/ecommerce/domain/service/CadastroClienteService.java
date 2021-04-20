package com.andre.ecommerce.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andre.ecommerce.api.dto.ClienteDto;
import com.andre.ecommerce.domain.exception.NegocioException;
import com.andre.ecommerce.domain.model.Cliente;
import com.andre.ecommerce.domain.repository.ClienteRepository;
import com.andre.ecommerce.domain.repository.EnderecoRepository;

@Service
public class CadastroClienteService {

	private static final String CLIENTE_NAO_EXISTE = "Cliente não existe";

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public ClienteDto salvar(Cliente cliente) {
		
		if (clienteRepository.existsByDadosGeraisCpfCnpj(cliente.getDadosGerais().getCpfCnpj())) {
			throw new NegocioException("Cliente já cadastrado");
		}
		
		Cliente clienteRetorno = clienteRepository.save(cliente);

		return toDto(clienteRetorno);
	}
	
	public List<ClienteDto> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		
		return toDtoList(listaClientes);
	}
	
	@Transactional
	public ClienteDto atualizarCliente(long clienteId, Cliente cliente) {
		existePorId(clienteId);
		
		cliente.setId(clienteId);
		clienteRepository.save(cliente);
		
		return toDto(cliente);
	}
	
	public ClienteDto buscarCliente(long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException(CLIENTE_NAO_EXISTE));
		
		return toDto(cliente);
	}
	
	public void existePorId(Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) { 
			throw new NegocioException(CLIENTE_NAO_EXISTE);
		};
	}
	
	private ClienteDto toDto(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDto.class);
	}

	private List<ClienteDto> toDtoList(List<Cliente> listaCliente) {
		return listaCliente.stream().map(cliente -> toDto(cliente)).collect(Collectors.toList());
	}
}
