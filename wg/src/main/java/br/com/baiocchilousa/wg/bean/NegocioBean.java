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

import br.com.baiocchilousa.wg.dao.ClienteDAO;
import br.com.baiocchilousa.wg.dao.NegocioDAO;
import br.com.baiocchilousa.wg.dao.TipoNegocioDAO;
import br.com.baiocchilousa.wg.domain.Cidade;
import br.com.baiocchilousa.wg.domain.Cliente;
import br.com.baiocchilousa.wg.domain.Imovel;
import br.com.baiocchilousa.wg.domain.Negocio;
import br.com.baiocchilousa.wg.domain.TipoNegocio;
import br.com.baiocchilousa.wg.domain.Usuario;

/**
 * Classe Managed Bean do domínio Negocio.
 * 
 * @param inicio()   Método para iniciar a tela de Cadastrar Negócio
 * @param novo() Método que cria um novo negócio
 * @param salvar() Método que salva um novo imóvel
 * @param editar() Método que edita um imóvel existente
 * @author     Leonardo Baiocchi Lousa
 * @version    2.0
 */

@ManagedBean
@ViewScoped
public class NegocioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480448372753728908L;
	
	private List<Negocio> negocios;
	private List<Negocio> negociosFiltrados;
	private List<TipoNegocio> tipos;
	private TipoNegocio tipo;
	private Negocio negocio;
	private List<Cliente> clientes;
	private List<Imovel> imoveis;
	private Date hoje = new Date();

	private boolean flagAtivar;

	@PostConstruct
	public void inicio() {

		try {
			NegocioDAO negocioDAO = new NegocioDAO();
			negocios = negocioDAO.listar("tsRegistro", true);

			TipoNegocioDAO tipoNegocioDAO = new TipoNegocioDAO();
			tipos = tipoNegocioDAO.listar("nome", false);
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("tsRegistro", true);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {

		negocio = new Negocio();

		try {
			TipoNegocioDAO tipoNegocioDAO = new TipoNegocioDAO();
			tipos = tipoNegocioDAO.listar("nome", false);
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("tsRegistro", true);
			
			imoveis = new ArrayList<Imovel>();
			
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void salvar() {

		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			NegocioDAO negocioDAO = new NegocioDAO();
			
			if(negocio.getId() != null){
				negocio.setTsAtualizacao(new Date());
				negocio.setUsuarioAtualizacao(usuario);
			}else{
				negocio.setTsRegistro(new Date());
				negocio.setUsuarioRegistro(usuario);
			}
			
			negocioDAO.merge(negocio);

			Messages.addGlobalInfo("Negócio " + negocio.getTitulo() + " gravado com sucesso!");
			
			negocio = new Negocio();

			TipoNegocioDAO tipoNegocioDAO = new TipoNegocioDAO();

			tipos = tipoNegocioDAO.listar("nome", false);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	
	public void editar(ActionEvent evento) {
		try {
			negocio = (Negocio) evento.getComponent().getAttributes().get("negocioSelecionado");
			tipo = negocio.getTipoNegocio();
			TipoNegocioDAO tipoNegocioDAO = new TipoNegocioDAO();
			tipos = tipoNegocioDAO.listar("nome", false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void prepararAtivaDesativa() {
		// Ativar/Desativar
		flagAtivar = negocio.getAtivo();
	}

	public void ativarDesativar() {

		try {
			NegocioDAO negocioDAO = new NegocioDAO();
			// negocioDAO.ativarDesativar(negocio);
			negocios = negocioDAO.listar("tsRegistro", true);
			flagAtivar = !negocio.getAtivo();
			String msg = "";
			if (flagAtivar) {
				msg = "Negócio ATIVADO com sucesso!";
			} else {
				msg = "Negócio DESATIVADO com sucesso!";
			}
			Messages.addGlobalInfo(msg);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			NegocioDAO negocioDAO = new NegocioDAO();
			negocioDAO.excluir(negocio);
			negocios = negocioDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Negócio " + negocio.getTitulo() + " excluído com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public List<Negocio> getNegocios() {
		return negocios;
	}

	public void setNegocios(List<Negocio> negocios) {
		this.negocios = negocios;
	}

	public List<Negocio> getNegociosFiltrados() {
		return negociosFiltrados;
	}

	public void setNegociosFiltrados(List<Negocio> negociosFiltrados) {
		this.negociosFiltrados = negociosFiltrados;
	}

	public List<TipoNegocio> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoNegocio> tipos) {
		this.tipos = tipos;
	}

	public TipoNegocio getTipo() {
		return tipo;
	}

	public void setTipo(TipoNegocio tipo) {
		this.tipo = tipo;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public boolean isFlagAtivar() {
		return flagAtivar;
	}

	public void setFlagAtivar(boolean flagAtivar) {
		this.flagAtivar = flagAtivar;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	

}
