package br.com.baiocchilousa.brewer.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.baiocchilousa.brewer.model.Estilo;
import br.com.baiocchilousa.brewer.repository.filter.EstiloFilter;

/**
 * Interface com outro tipo de busca no banco de dados
 * @author leolo
 *
 */
public interface EstilosQueries {

	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
	
}
