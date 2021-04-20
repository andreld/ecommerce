package com.andre.ecommerce.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.ecommerce.api.dto.TransportadoraDto;
import com.andre.ecommerce.api.input.TransportadoraInput;
import com.andre.ecommerce.domain.model.DadosGerais;
import com.andre.ecommerce.domain.model.Transportadora;
import com.andre.ecommerce.domain.service.CadastroTransportadoraService;

@RequestMapping("/transportadora")
@RestController
public class TransportadoraController {

	@Autowired
	CadastroTransportadoraService cadastroTransportadoraService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping
	public TransportadoraDto criar(@Valid @RequestBody TransportadoraInput pessoaInput) {
		return cadastroTransportadoraService.salvar(toEntity(pessoaInput));
	}

	@GetMapping
	public List<TransportadoraDto> listar() {
		return cadastroTransportadoraService.listarTransportadoras();
	}

	@GetMapping("/{transportadoraId}")
	public TransportadoraDto buscar(@PathVariable long transportadoraId) {
		return cadastroTransportadoraService.buscarTransportadora(transportadoraId);
	}

	@PutMapping("/{transportadoraId}")
	public TransportadoraDto atualizar(@PathVariable long transportadoraId,
			@Valid @RequestBody TransportadoraInput transportadoraInput) {
		return cadastroTransportadoraService.atualizar(transportadoraId, toEntity(transportadoraInput));
	}

	private Transportadora toEntity(TransportadoraInput transportadoraInput) {
		DadosGerais dadosGerais = modelMapper.map(transportadoraInput, DadosGerais.class);
		Transportadora transportadora = modelMapper.map(transportadoraInput, Transportadora.class);
		transportadora.setDadosGerais(dadosGerais);

		return transportadora;
	}

}
