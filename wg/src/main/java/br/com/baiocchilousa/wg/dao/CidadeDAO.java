package br.com.baiocchilousa.wg.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.baiocchilousa.wg.domain.Cidade;
import br.com.baiocchilousa.wg.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaPorUf(Long id, String campoOrdenacao, boolean descendente){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("uf.id", id));
			if(descendente){
				consulta.addOrder(Order.desc(campoOrdenacao));	
			}else{
				consulta.addOrder(Order.asc(campoOrdenacao));
			}
			List<Cidade> lista = consulta.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}	
	}

}
