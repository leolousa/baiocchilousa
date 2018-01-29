package br.com.baiocchilousa.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.baiocchilousa.brewer.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
