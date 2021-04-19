package com.andre.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByDadosGeraisCpfCnpj(String cpfCnpj);
	public boolean existsByDadosGeraisCpfCnpj(String cpfCnpj);
}
