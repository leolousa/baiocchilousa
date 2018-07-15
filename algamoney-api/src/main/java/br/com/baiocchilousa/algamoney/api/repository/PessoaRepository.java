package br.com.baiocchilousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
