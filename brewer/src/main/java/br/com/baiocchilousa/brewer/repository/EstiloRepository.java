package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.baiocchilousa.brewer.model.Estilo;
import br.com.baiocchilousa.brewer.repository.helper.estilo.EstilosQueries;

/**
 * Classe repositório para acesso ao banco de dados com a
 * implementação das interfaces JpaRepository e EstilosQueries
 * para utilizar JPA e Criteria, por exemplo com uma só classe injetada
 * do repositório EstiloRepository
 * 
 * @author leolo
 *
 */
@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>, EstilosQueries{

	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
