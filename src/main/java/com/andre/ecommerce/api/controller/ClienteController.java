package com.andre.ecommerce.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.ecommerce.api.dto.ClienteDto;
import com.andre.ecommerce.api.input.ClienteInput;
import com.andre.ecommerce.domain.model.Cliente;
import com.andre.ecommerce.domain.model.DadosGerais;
import com.andre.ecommerce.domain.service.CadastroClienteService;

@RequestMapping("/")
@RestController
public class ClienteController {
	
	@Autowired
	CadastroClienteService cadastroClienteService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("cliente")
	public ClienteDto criar(@Valid @RequestBody ClienteInput pessoaInput) {
		return cadastroClienteService.salvar(toEntity(pessoaInput));
	}
	
	@GetMapping("cliente")
	public List<ClienteDto> listar(){
		return cadastroClienteService.listarClientes();		
	}
	
	private Cliente toEntity(ClienteInput pessoaInput) {
		DadosGerais dadosGerais = modelMapper.map(pessoaInput, DadosGerais.class);
		Cliente cliente = modelMapper.map(pessoaInput, Cliente.class);
		cliente.setDadosGerais(dadosGerais);
		
		return cliente;
	}

}
