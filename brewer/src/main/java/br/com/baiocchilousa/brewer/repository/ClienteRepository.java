package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Cliente;
import br.com.baiocchilousa.brewer.repository.helper.cliente.ClientesQueries;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClientesQueries{

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
