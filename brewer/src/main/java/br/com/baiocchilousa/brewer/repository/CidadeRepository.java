package br.com.baiocchilousa.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Cidade;
import br.com.baiocchilousa.brewer.model.Estado;
import br.com.baiocchilousa.brewer.repository.helper.cidade.CidadesQueries;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadesQueries{
	
	//O Spring retorna uma consulta no seguinte formato findBy<Objeto.propriedade>()
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	//O Spring retorna uma consulta no seguinte formato findBy<Propriedade>And<OutraPropriedade>()
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
