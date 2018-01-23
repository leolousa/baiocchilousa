package br.com.baiocchilousa.brewer.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.filter.CervejaFilter;

/**
 * Interface com outro tipo de busca no banco de dados
 * @author leolo
 *
 */
public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
}
