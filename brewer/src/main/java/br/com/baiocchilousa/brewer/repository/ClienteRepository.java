package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
