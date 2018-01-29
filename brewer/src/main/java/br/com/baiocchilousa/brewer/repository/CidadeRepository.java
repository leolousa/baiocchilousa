package br.com.baiocchilousa.brewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
}
