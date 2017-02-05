package br.com.baiocchilousa.wg.dao;

import org.junit.Test;

import br.com.baiocchilousa.wg.domain.Pessoa;
import br.com.baiocchilousa.wg.domain.TipoUsuario;
import br.com.baiocchilousa.wg.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	public void salvar(){
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
		TipoUsuario tipoUsuario = tipoUsuarioDAO.buscar(1L);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setApelido("Leo");
		usuario.setPessoa(pessoa);
		usuario.setTipo(tipoUsuario);
		usuario.setSenha("teste");
		
	}
}
