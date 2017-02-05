package br.com.baiocchilousa.wg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.baiocchilousa.wg.domain.Usuario;
import br.com.baiocchilousa.wg.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{

	public void ativar(Usuario u){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			u.setAtivo(true);
			sessao.update(u);
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
