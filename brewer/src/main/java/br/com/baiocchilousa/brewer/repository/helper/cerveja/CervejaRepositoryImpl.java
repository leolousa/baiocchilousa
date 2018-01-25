package br.com.baiocchilousa.brewer.repository.helper.cerveja;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.filter.CervejaFilter;
import br.com.baiocchilousa.brewer.repository.paginacao.PaginacaoUtil;

/**
 * Classe com uma maneira de implementar os metodos de filtragem
 * da interface CervejaQueries na implementação da classe CervejasRepository 
 * 
 * O Criteria do Hibernate foi utilizado para diminuir as consultas ao banco de dados.
 * 
 * Ex: Quando exibimos uma lista de Cervejas, já faz o inner join (fetch) para trazer o nome do estilo,
 * evitando fazer um select a cada linha da lista, quando utilizamos JPA.
 * 
 * @author leolo
 *
 */
public class CervejaRepositoryImpl implements CervejasQueries {

	//Injetando o EntityManager
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable) {
		
		//Criteria do Hibernate
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionaFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	
	private Long total(CervejaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionaFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	private void adicionaFiltro(CervejaFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(isEstiloPresente(filtro)) {
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo()));
			}
			
			if(filtro.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}
			
			if(filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}
			
			if(filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}

			if(filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}

		}
	}


	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}
	
}
