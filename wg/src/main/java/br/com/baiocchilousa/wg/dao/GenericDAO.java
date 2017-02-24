package br.com.baiocchilousa.wg.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.baiocchilousa.wg.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	// Reflection para pegar o tipo da classe da entidade encaminhada ao GenericDAO<Entidade>
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> lista = consulta.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Entidade> listar(String campoOrdenacao, boolean descendente){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			if(descendente){
				consulta.addOrder(Order.desc(campoOrdenacao));	
			}else{
				consulta.addOrder(Order.asc(campoOrdenacao));
			}
			List<Entidade> lista = consulta.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	public Entidade buscar(Long id){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(id));
			Entidade entidade = (Entidade) consulta.uniqueResult();
			return entidade;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Entidade> buscar(String campo, Long valor){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.eq(campo, valor));
			List<Entidade> lista = (List<Entidade>) consulta.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	
	public void excluir(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public void editar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	public void merge(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
