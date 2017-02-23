package br.com.baiocchilousa.wg.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.baiocchilousa.wg.dao.ImovelDAO;
import br.com.baiocchilousa.wg.dao.TipoImovelDAO;
import br.com.baiocchilousa.wg.domain.Imovel;
import br.com.baiocchilousa.wg.domain.TipoImovel;
import br.com.baiocchilousa.wg.domain.Usuario;

/**
 * Classe Managed Bean do domínio Imovel.
 * 
 * @param preparaImoveis()   Método para iniciar a tela de Cadastrar Imóveis
 * @param novo() Método que cria um novo imóvel
 * @param salvar() Método que salva um novo imóvel
 * @param editar() Método que edita um imóvel existente
 * @author     Leonardo Baiocchi Lousa
 * @version    2.0
 */

@ManagedBean
@ViewScoped
public class ImovelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1798891452477731917L;

	private List<Imovel> imoveis;
	private List<Imovel> imoveisFiltrados;
	private List<TipoImovel> tipos;
	private Imovel imovel;

	private boolean flagAtivar;

	@PostConstruct
	public void inicio() {

		try {
			ImovelDAO dao = new ImovelDAO();
			imoveis = dao.listar("tsRegistro", true);

			TipoImovelDAO daoTipo = new TipoImovelDAO();
			tipos = daoTipo.listar("nome", false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void novo() {

		imovel = new Imovel();

		try {
			TipoImovelDAO daoTipo = new TipoImovelDAO();
			tipos = daoTipo.listar("nome", false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	public void salvar() {

		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			ImovelDAO imovelDAO = new ImovelDAO();
			imovel.setAtivo(true);
			imovel.setTsRegistro(new Date());
			imovel.setUsuarioRegistro(usuario);
			imovelDAO.merge(imovel);

			Messages.addGlobalInfo("Imóvel " + imovel.getNome() + " gravado com sucesso!");
			
			imovel = new Imovel();

			TipoImovelDAO tipoImovelDAO = new TipoImovelDAO();

			tipos = tipoImovelDAO.listar("nome", false);
			imoveis = imovelDAO.listar("tsRegistro", true);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}

	}

	
	public void editar() {
		
		// Usuário fake - fazer a classe que traz o usuario
		Usuario usuario = new Usuario();

		usuario.setId(1L);

		try {
			ImovelDAO imovelDAO = new ImovelDAO();

			imovel.setAtivo(true);
			imovel.setTsAtualizacao(new Date());
			imovel.setUsuarioAtualizacao(usuario);

			imovelDAO.editar(imovel);

			//imovel = new Imovel();

			TipoImovelDAO tipoImovelDAO = new TipoImovelDAO();

			tipos = tipoImovelDAO.listar("nome", false);
			imoveis = imovelDAO.listar("tsRegistro", true);
			
			Messages.addGlobalInfo("Imóvel " + imovel.getNome() + " atualizado com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void prepararAtivaDesativa() {
		// Ativar/Desativar
		flagAtivar = imovel.getAtivo();
	}

	public void ativarDesativar() {

		try {
			ImovelDAO dao = new ImovelDAO();
			// dao.ativarDesativar(imovel);
			imoveis = dao.listar("tsRegistro", true);
			flagAtivar = !imovel.getAtivo();
			String msg = "";
			if (flagAtivar) {
				msg = "Imóvel ATIVADO com sucesso!";
			} else {
				msg = "Imóvel DESATIVADO com sucesso!";
			}
			Messages.addGlobalInfo(msg);

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ImovelDAO imovelDAO = new ImovelDAO();
			imovelDAO.excluir(imovel);
			imoveis = imovelDAO.listar("tsRegistro", true);
			Messages.addGlobalInfo("Imóvel " + imovel.getNome() + " excluído com sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro: " + e.getMessage());
		}
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public boolean isFlagAtivar() {
		return flagAtivar;
	}

	public void setFlagAtivar(boolean flagAtivar) {
		this.flagAtivar = flagAtivar;
	}

	public List<Imovel> getImoveisFiltrados() {
		return imoveisFiltrados;
	}

	public void setImoveisFiltrados(List<Imovel> imoveisFiltrados) {
		this.imoveisFiltrados = imoveisFiltrados;
	}

	public List<TipoImovel> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoImovel> tipos) {
		this.tipos = tipos;
	}

}
