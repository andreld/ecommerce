package com.andre.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

}
