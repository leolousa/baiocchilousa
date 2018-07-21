package br.com.baiocchilousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.algamoney.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
