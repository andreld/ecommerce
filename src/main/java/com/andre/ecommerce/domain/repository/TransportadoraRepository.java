package com.andre.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long>{

}
