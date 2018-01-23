package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.helper.cerveja.CervejasQueries;
/**
 * Classe repositório para acesso ao banco de dados com a
 * implementação das interfaces JpaRepository e CervejasQueries
 * para utilizar JPA e Criteria, por exemplo com uma só classe injetada
 * do repositório CervejaRepository
 * 
 * @author leolo
 *
 */
@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Long>, CervejasQueries{

	// Optional -> objeto do Java8 que trata o retorno nulo, ela é opcional.
	// Não é necessário implementar o método pois o Spring já abstrai isso
	public Optional<Cerveja> findBySkuIgnoreCase(String sku);
	
}
