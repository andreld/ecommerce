package com.andre.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>{

}
