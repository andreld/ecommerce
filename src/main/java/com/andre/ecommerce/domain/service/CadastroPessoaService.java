package com.andre.ecommerce.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.ecommerce.api.dto.PessoaDto;
import com.andre.ecommerce.domain.exception.NegocioException;
import com.andre.ecommerce.domain.model.Pessoa;
import com.andre.ecommerce.domain.model.TipoUsuario;
import com.andre.ecommerce.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	ModelMapper modelMapper;

	public PessoaDto salvar(Pessoa pessoa) {
		if (pessoaRepository.existsByCpfCnpj(pessoa.getCpfCnpj())) {
			if (pessoa.getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
				throw new NegocioException("Cliente já cadastrado");
			}

			if (pessoa.getTipoUsuario().equals(TipoUsuario.TRANSPORTADORA)) {
				throw new NegocioException("Transportadora já cadastrada");
			}
		}

		Pessoa pessoaRetorno = pessoaRepository.save(pessoa);

		return toDto(pessoaRetorno);
	}
	
	public List<PessoaDto> listarClientes() {
		List<Pessoa> listaPessoas = pessoaRepository.findByTipoUsuario(TipoUsuario.CLIENTE.ordinal());
		
		return toDtoList(listaPessoas);
	}
	
	public List<PessoaDto> listarTransportadoras() {
		List<Pessoa> listaPessoas = pessoaRepository.findByTipoUsuario(TipoUsuario.TRANSPORTADORA.ordinal());
		
		return toDtoList(listaPessoas);
	}

	private PessoaDto toDto(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaDto.class);
	}

	private List<PessoaDto> toDtoList(List<Pessoa> listaPessoa) {
		return listaPessoa.stream().map(pessoa -> toDto(pessoa)).collect(Collectors.toList());
	}
}
