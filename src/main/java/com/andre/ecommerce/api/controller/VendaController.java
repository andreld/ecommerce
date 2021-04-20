package com.andre.ecommerce.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.ecommerce.api.dto.VendaDto;
import com.andre.ecommerce.api.input.VendaInput;
import com.andre.ecommerce.domain.model.EstoqueProduto;
import com.andre.ecommerce.domain.model.ItemVenda;
import com.andre.ecommerce.domain.model.Venda;
import com.andre.ecommerce.domain.service.CadastroClienteService;
import com.andre.ecommerce.domain.service.CadastroEstoqueProdutoService;
import com.andre.ecommerce.domain.service.GestaoVendaService;

@RequestMapping("/cliente/{clienteId}/venda")
@RestController
public class VendaController {
	
	@Autowired
	GestaoVendaService gestaoVendaService;
	
	@Autowired
	CadastroClienteService cadastroClienteService;
	
	@Autowired 
	CadastroEstoqueProdutoService cadastroEstoqueProdutoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/historico")
	public List<VendaDto> historico(@PathVariable Long clienteId) {
		return gestaoVendaService.listar(clienteId);
	}
	
	@PostMapping("/checkout")
	public VendaDto checkout(@PathVariable Long clienteId, @Valid @RequestBody VendaInput vendaInput) {
		cadastroClienteService.existePorId(clienteId);
		return gestaoVendaService.checkout(toEntity(vendaInput), clienteId);
	}
	
	@PostMapping("/finalizar")
	public VendaDto finalizar(@PathVariable Long clienteId, @Valid @RequestBody VendaInput vendaInput) {
		cadastroClienteService.existePorId(clienteId);
		return gestaoVendaService.salvar(toEntity(vendaInput), clienteId);
	}
	
	private Venda toEntity(VendaInput vendaInput) {
		Venda venda = modelMapper.map(vendaInput, Venda.class);
		
		vendaInput.getItensCarrinho().forEach(item -> {
			EstoqueProduto estoqueProduto = new EstoqueProduto();
			estoqueProduto.setId(item.getEstoqueProdutoId());
			
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setEstoqueProduto(estoqueProduto);
			itemVenda.setQuantidade(item.getQuantidade());
			
			venda.getItensCarrinho().add(itemVenda);
		});
		
		return venda;
	}
}
