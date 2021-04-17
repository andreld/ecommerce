package com.andre.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.ecommerce.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	public Pessoa findByCpfCnpj(String cpfCnpj);
	public boolean existsByCpfCnpj(String cpfCnpj);
	public List<Pessoa> findByTipoUsuario(int cpfCnpj);
}
