package br.com.baiocchilousa.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.baiocchilousa.brewer.model.Cliente;
import br.com.baiocchilousa.brewer.repository.filter.ClienteFilter;

/**
 * Interface com outro tipo de busca no banco de dados
 * @author leolo
 *
 */
public interface ClientesQueries {
	
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);

}
