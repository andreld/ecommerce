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

import com.andre.ecommerce.api.dto.PessoaDto;
import com.andre.ecommerce.api.input.PessoaInput;
import com.andre.ecommerce.domain.model.Pessoa;
import com.andre.ecommerce.domain.model.TipoUsuario;
import com.andre.ecommerce.domain.service.CadastroPessoaService;

@RequestMapping("/")
@RestController
public class PessoaController {
	
	@Autowired
	CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/cliente/cadastrar")
	public PessoaDto criar(@Valid @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = toEntity(pessoaInput);
		pessoa.setTipoUsuario(TipoUsuario.CLIENTE);
		return cadastroPessoaService.salvar(pessoa);
	}
	
	//Todos os clientes
	@GetMapping("/clientes")
	public List<PessoaDto> listar(){
		return cadastroPessoaService.listarClientes();		
	}
	
	private Pessoa toEntity(@Valid PessoaInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}

}
