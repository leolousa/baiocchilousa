package br.com.baiocchilousa.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.baiocchilousa.brewer.dto.CervejaDTO;
import br.com.baiocchilousa.brewer.dto.ValorItensEstoque;
import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.filter.CervejaFilter;
import br.com.baiocchilousa.brewer.repository.paginacao.PaginacaoUtil;
import br.com.baiocchilousa.brewer.storage.FotoStorage;

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
	
	@Autowired
	private FotoStorage fotoStorage;
	
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
	
	@Override
	public List<CervejaDTO> porSkuOuNome(String skuOuNome) {
		
		String jpql = "SELECT new br.com.baiocchilousa.brewer.dto.CervejaDTO(codigo, sku, nome, origem, valor, foto) "
				+ "FROM Cerveja WHERE lower(sku) LIKE lower(:skuOuNome) OR lower(nome) LIKE lower(:skuOuNome)";
		
		List<CervejaDTO> cervejasFiltradas = manager.createQuery(jpql, CervejaDTO.class)
				.setParameter("skuOuNome", skuOuNome + "%")
				.getResultList();
		cervejasFiltradas.forEach(c -> c.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + c.getFoto())));
		return cervejasFiltradas;
	}

	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new br.com.baiocchilousa.brewer.dto.ValorItensEstoque(sum(valor * quantidadeEstoque), sum(quantidadeEstoque)) from Cerveja";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
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
			
			criteria.add(Restrictions.gt("quantidadeEstoque", 0));

		}
	}

	
	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

}
