package br.com.baiocchilousa.wg.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.baiocchilousa.wg.dao.CidadeDAO;
import br.com.baiocchilousa.wg.dao.PessoaDAO;
import br.com.baiocchilousa.wg.domain.Cidade;
import br.com.baiocchilousa.wg.domain.Pessoa;
import br.com.baiocchilousa.wg.domain.Usuario;



/**
 * Classe Managed Bean do domínio Pessoa.
 * 
 * @param preparaPessoas()   Método para iniciar a tela de Cadastrar Pessoas
 * @param novo() Método que cria uma nova pessoa
 * @param salvar() Método que salva uma nova pessoa
 * @param editar() Método que edita uma pessoa existente
 * @author     Leonardo Baiocchi Lousa
 * @version    1.0
 */

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5285162342714753387L;
	
	private List<Pessoa> pessoas;
	private List<Pessoa> pessoasFiltradas;
	private List<Cidade> cidades;
	private Pessoa pessoa;
	
	
	@PostConstruct
	public void preparaPessoas() {

		try {
			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar("tsRegistro", true);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {

		pessoa = new Pessoa();

		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			setCidades(cidadeDAO.listar("nome", false));
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}

	}

	public void salvar() {

		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa.setTsRegistro(new Date());
			pessoa.setUsuarioRegistro(usuario.getId());
			pessoaDAO.merge(pessoa);

			pessoa = new Pessoa();

			CidadeDAO cidadeDAO = new CidadeDAO();

			cidades = cidadeDAO.listar("nome", false);
			pessoas = pessoaDAO.listar("tsRegistro", true);

			Messages.addGlobalInfo("Pessoa gravado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}

	}

	
	public void editar() {
		
		// Usuário fake - fazer a classe que traz o usuario
		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();

			pessoa.setTsAtualizacao(new Date());
			pessoa.setUsuarioAtualizacao(usuario.getId());

			pessoaDAO.editar(pessoa);

			//pessoa = new pessoa();

			CidadeDAO cidadeDAO = new CidadeDAO();

			cidades = cidadeDAO.listar("nome", false);
			pessoas = pessoaDAO.listar("tsRegistro", true);
			
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " atualizada com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			pessoas = pessoaDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " excluído com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public List<Pessoa> getPessoasFiltradas() {
		return pessoasFiltradas;
	}
	public void setPessoasFiltradas(List<Pessoa> pessoasFiltradas) {
		this.pessoasFiltradas = pessoasFiltradas;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
	
}
