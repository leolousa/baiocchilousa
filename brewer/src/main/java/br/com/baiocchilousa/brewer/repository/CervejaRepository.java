package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.baiocchilousa.brewer.model.Cerveja;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Long>{

	// Optional objeto do Java8 que trata o retorno nulo, ela é opcional.
	// Não é necessário implementar o método pois o Spring já abstrai isso
	public Optional<Cerveja> findBySkuIgnoreCase(String sku);
	
}
