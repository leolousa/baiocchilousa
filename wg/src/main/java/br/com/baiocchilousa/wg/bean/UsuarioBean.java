package br.com.baiocchilousa.wg.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.baiocchilousa.wg.dao.PessoaDAO;
import br.com.baiocchilousa.wg.dao.TipoUsuarioDAO;
import br.com.baiocchilousa.wg.dao.UsuarioDAO;
import br.com.baiocchilousa.wg.domain.Pessoa;
import br.com.baiocchilousa.wg.domain.TipoUsuario;
import br.com.baiocchilousa.wg.domain.Usuario;

/**
 * Classe Managed Bean do domínio Usuario.
 * 
 * @param preparaUsuarios()
 *            Método para iniciar a tela de Cadastrar Usuário
 * @param novo()
 *            Método que cria um novo usuário
 * @param salvar()
 *            Método que salva um novo usuário
 * @param editar()
 *            Método que edita um usuário existente
 * @author Leonardo Baiocchi Lousa
 * @version 2.0
 */

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private List<Usuario> usuariosFiltrados;
	private List<TipoUsuario> tipos;
	private boolean flagAtivar;

	@PostConstruct
	public void preparaUsuarios() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("tsRegistro", true);

			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipos = tipoUsuarioDAO.listar("nome", false);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {
		
		usuario = new Usuario();
		
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome", false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
		

		

	}

	public void novoUsuario() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
			usuarioDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Usuário gravado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}

	}

	public void prepararAtivaDesativa() {
		// Ativar/Desativar
		flagAtivar = usuario.getAtivo();
	}

	public void ativarDesativar() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.ativar(usuario);
			usuarios = usuarioDAO.listar("tsRegistro", true);
			flagAtivar = !usuario.getAtivo();
			String msg = "";
			if (flagAtivar) {
				msg = "Usuário ATIVADO com sucesso!";
			} else {
				msg = "Usuário DESATIVADO com sucesso!";
			}
			Messages.addGlobalInfo(msg);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			usuarios = usuarioDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Usuário " + usuario.getPessoa().getNome() + " excluído com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.editar(usuario);
			usuarios = usuarioDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Usuário " + usuario.getPessoa().getNome() + " atualizado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public List<TipoUsuario> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoUsuario> tipos) {
		this.tipos = tipos;
	}

	public boolean isFlagAtivar() {
		return flagAtivar;
	}

	public void setFlagAtivar(boolean flagAtivar) {
		this.flagAtivar = flagAtivar;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
