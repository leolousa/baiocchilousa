package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.baiocchilousa.brewer.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>{

	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
