package com.andre.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Cliente;
import com.andre.ecommerce.domain.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

	List<Venda> findByCliente(Cliente cliente);

}
