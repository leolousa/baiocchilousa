package br.com.baiocchilousa.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Venda;
import br.com.baiocchilousa.brewer.repository.helper.venda.VendasQueries;

public interface VendaRepository extends JpaRepository<Venda, Long>, VendasQueries {


}
