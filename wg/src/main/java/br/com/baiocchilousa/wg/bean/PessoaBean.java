package br.com.baiocchilousa.wg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.baiocchilousa.wg.dao.CidadeDAO;
import br.com.baiocchilousa.wg.dao.PessoaDAO;
import br.com.baiocchilousa.wg.dao.UfDAO;
import br.com.baiocchilousa.wg.domain.Cidade;
import br.com.baiocchilousa.wg.domain.Pessoa;
import br.com.baiocchilousa.wg.domain.Uf;
import br.com.baiocchilousa.wg.domain.Usuario;

/**
 * Classe Managed Bean do domínio Pessoa.
 * 
 * @param inicio()  Método para iniciar a tela de Cadastrar Pessoas
 * @param novo() Método que cria uma nova pessoa
 * @param salvar() Método que salva uma nova pessoa
 * @param editar() Método que edita uma pessoa existente
 * @author Leonardo Baiocchi Lousa
 * @version 1.0
 */

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5285162342714753387L;

	private List<Pessoa> pessoas;
	private List<Pessoa> pessoasFiltradas;
	private List<Cidade> cidades;
	private Uf uf;
	private List<Uf> estados;
	private Pessoa pessoa;

	@PostConstruct
	public void inicio() {

		try {
			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar("tsRegistro", true);

			UfDAO ufDAO = new UfDAO();
			estados = ufDAO.listar("nome", false);

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar("nome", false);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			uf = new Uf();
			UfDAO ufDAO = new UfDAO();
			
			estados = ufDAO.listar("nome", false);
			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void salvar() {
		// TODO: implemantar o usuário, fazer a classe que traz o usuario
		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			
			if(pessoa.getId() != null){
				pessoa.setTsAtualizacao(new Date());
				pessoa.setUsuarioAtualizacao(usuario.getId());
			}else{
				pessoa.setTsRegistro(new Date());
				pessoa.setUsuarioRegistro(usuario.getId());
			}
			
			pessoaDAO.merge(pessoa);
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " gravada com sucesso!");

			pessoa = new Pessoa();
			uf = new Uf();

			CidadeDAO cidadeDAO = new CidadeDAO();

			cidades = cidadeDAO.listar("nome", false);
			pessoas = pessoaDAO.listar("tsRegistro", true);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			uf = pessoa.getCidade().getUf();
			UfDAO ufDAO = new UfDAO();
			estados = ufDAO.listar("nome", false);
			populaCombo();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			pessoas = pessoaDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " excluída com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void populaCombo() {
		try {
			if (uf != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscaPorUf(uf.getId(), "nome", false);
			} else {
				cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
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

	public List<Uf> getEstados() {
		return estados;
	}

	public void setEstados(List<Uf> estados) {
		this.estados = estados;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}
