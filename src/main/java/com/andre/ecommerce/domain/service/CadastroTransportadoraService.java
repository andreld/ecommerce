package com.andre.ecommerce.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andre.ecommerce.api.dto.TransportadoraDto;
import com.andre.ecommerce.domain.exception.NegocioException;
import com.andre.ecommerce.domain.model.Transportadora;
import com.andre.ecommerce.domain.repository.EnderecoRepository;
import com.andre.ecommerce.domain.repository.TransportadoraRepository;

@Service
public class CadastroTransportadoraService {

	private static final String TRANSPORTADORA_NAO_EXISTE = "Transportadora não existe";

	@Autowired
	TransportadoraRepository transportadoraRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public TransportadoraDto salvar(Transportadora transportadora) {
		
		if (transportadoraRepository.existsByDadosGeraisCpfCnpj(transportadora.getDadosGerais().getCpfCnpj())) {
			throw new NegocioException("Transportadora já cadastrada");
		}
		
		Transportadora transportadoraRetorno = transportadoraRepository.save(transportadora);

		return toDto(transportadoraRetorno);
	}
	
	@Transactional
	public TransportadoraDto atualizar(long transportadoraId, Transportadora transportadora) {
		
		existePorId(transportadoraId);
		transportadora.setId(transportadoraId);
		
		return toDto(transportadoraRepository.save(transportadora));
	}
	
	public List<TransportadoraDto> listarTransportadoras() {
		List<Transportadora> listaTransportadoras = transportadoraRepository.findAll();
		
		return toDtoList(listaTransportadoras);
	}
	
	public TransportadoraDto buscarTransportadora(long transportadoraId) {
		Transportadora transportadora = transportadoraRepository.findById(transportadoraId)
				.orElseThrow(() -> new NegocioException(TRANSPORTADORA_NAO_EXISTE));
		
		return toDto(transportadora);
	}
	
	public void existePorId(Long transportadoraId) {
		if(!transportadoraRepository.existsById(transportadoraId)) { 
			throw new NegocioException(TRANSPORTADORA_NAO_EXISTE);
		};
	}
	
	private TransportadoraDto toDto(Transportadora transportadora) {
		return modelMapper.map(transportadora, TransportadoraDto.class);
	}

	private List<TransportadoraDto> toDtoList(List<Transportadora> listaTransportadora) {
		return listaTransportadora.stream().map(transportadora -> toDto(transportadora)).collect(Collectors.toList());
	}

}
