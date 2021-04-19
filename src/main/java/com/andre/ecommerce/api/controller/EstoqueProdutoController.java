package com.andre.ecommerce.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.ecommerce.api.dto.EstoqueProdutoDto;
import com.andre.ecommerce.api.input.EstoqueProdutoInput;
import com.andre.ecommerce.domain.model.EstoqueProduto;
import com.andre.ecommerce.domain.service.CadastroEstoqueProdutoService;

@RequestMapping("/estoque-produto")
@RestController
public class EstoqueProdutoController {
	
	@Autowired
	CadastroEstoqueProdutoService cadastroEstoqueProdutoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping
	public EstoqueProdutoDto criar(@Valid @RequestBody EstoqueProdutoInput estoqueProdutoInput) {
		EstoqueProduto estoqueProduto = toEntity(estoqueProdutoInput);
		
		return cadastroEstoqueProdutoService.salvar(estoqueProduto);
	}
	
	//Todos os estoques de produtos
	@GetMapping
	public ResponseEntity<List<EstoqueProdutoDto>>  listar(){
		return ResponseEntity.ok(cadastroEstoqueProdutoService.listar());		
	}
	
	@GetMapping("/{estoqueProdutoId}")
	public ResponseEntity<EstoqueProdutoDto> buscar(@PathVariable Long estoqueProdutoId){
		return ResponseEntity.ok(cadastroEstoqueProdutoService.buscarPorId(estoqueProdutoId));		
	}
	
	@PutMapping("/{estoqueProdutoId}")
	public EstoqueProdutoDto atualizar(@PathVariable Long estoqueProdutoId, @Valid @RequestBody EstoqueProdutoInput estoqueProdutoInput) {
		EstoqueProduto estoqueProduto = toEntity(estoqueProdutoInput);
		
		return cadastroEstoqueProdutoService.atualizar(estoqueProdutoId, estoqueProduto);
	}
	
	@DeleteMapping("/{estoqueProdutoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long estoqueProdutoId) {
		cadastroEstoqueProdutoService.excluir(estoqueProdutoId);
		
		return ResponseEntity.noContent().build();
	}
	
	private EstoqueProduto toEntity(@Valid EstoqueProdutoInput estoqueProdutoInput) {
		return modelMapper.map(estoqueProdutoInput, EstoqueProduto.class);
	}

}
