package br.com.baiocchilousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
