package br.com.baiocchilousa.wg.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.baiocchilousa.wg.dao.CidadeDAO;
import br.com.baiocchilousa.wg.dao.ClienteDAO;
import br.com.baiocchilousa.wg.dao.PessoaDAO;
import br.com.baiocchilousa.wg.domain.Cliente;
import br.com.baiocchilousa.wg.domain.Pessoa;
import br.com.baiocchilousa.wg.domain.Usuario;

/**
 * Classe Managed Bean do domínio Cliente.
 * 
 * @param inicio()   Método para iniciar a tela de Cadastrar Clientes
 * @param novo() Método que cria uma novo cliente
 * @param salvar() Método que salva um novo cliente
 * @param editar() Método que edita um cliente existente
 * @author     Leonardo Baiocchi Lousa
 * @version    1.0
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{

	private List<Cliente> clientes;
	private List<Cliente> clientesFiltrados;
	private Cliente cliente;
	private List<Pessoa> pessoas;
	private Pessoa pessoa;
	
	private boolean flagAtivar;
	
	@PostConstruct
	public void inicio() {

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("tsRegistro", true);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome", false);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void salvar() {
		// TODO: implemantar o usuário
		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa.setTsRegistro(new Date());
			pessoa.setUsuarioRegistro(usuario.getId());
			pessoaDAO.merge(pessoa);
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " gravada com sucesso!");
			
			pessoa = new Pessoa();

			
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
			Messages.addGlobalInfo("Pessoa " + pessoa.getNome() + " excluída com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro: " + e.getMessage());
		}
	}
	
	
	public void populaCombo(){
//		try {
//			if(uf != null){
//				CidadeDAO cidadeDAO = new CidadeDAO();
//				cidades = cidadeDAO.buscaPorUf(uf.getId(), "nome", false);
//			}else{
//				cidades = new ArrayList<Cidade>();
//			}
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			Messages.addFlashGlobalError("Erro: " + e.getMessage());
//		}
		
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public boolean isFlagAtivar() {
		return flagAtivar;
	}

	public void setFlagAtivar(boolean flagAtivar) {
		this.flagAtivar = flagAtivar;
	}
	
}
