package br.com.baiocchilousa.wg.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.baiocchilousa.wg.dao.ImovelDAO;
import br.com.baiocchilousa.wg.dao.TipoImovelDAO;
import br.com.baiocchilousa.wg.domain.Imovel;
import br.com.baiocchilousa.wg.domain.TipoImovel;
import br.com.baiocchilousa.wg.util.JSFUtil;

@ManagedBean(name = "MBImovel")
@ViewScoped
public class ImovelBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1798891452477731917L;
	
	
	private List<Imovel> imoveis;
	private List<Imovel> imoveisFiltrados;
	private Imovel imovel;
	private List<TipoImovel> tipos;
	private boolean flagAtivar;

	@PostConstruct
	public void preparaImoveis() {

		try {
			ImovelDAO dao = new ImovelDAO();
			imoveis = dao.listar();
			
			TipoImovelDAO daoTipo = new TipoImovelDAO();
			tipos = daoTipo.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Mensagem de erro: " + e.getMessage());
		}

	}

	public void prepararNovo() {
		imovel = new Imovel();
	}

	public void novoImovel() {

		try {
			ImovelDAO dao = new ImovelDAO();
			dao.salvar(imovel);
			dao.listar();
			JSFUtil.adicionarMensagemSucesso("Im�vel gravado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Mensagem de erro: " + e.getMessage());
		}

	}

	public void prepararAtivaDesativa() {
		// Ativar/Desativar
		flagAtivar = imovel.getAtivo();
	}

	public void ativarDesativar() {

		try {
			ImovelDAO dao = new ImovelDAO();
			//dao.ativarDesativar(imovel);
			imoveis = dao.listar();
			flagAtivar = !imovel.getAtivo();
			String msg = "";
			if (flagAtivar) {
				msg = "Imóvel ATIVADO com sucesso!";
			} else {
				msg = "Imóvel DESATIVADO com sucesso!";
			}
			JSFUtil.adicionarMensagemSucesso(msg);

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Mensagem de erro: " + e.getMessage());
		}
	}

	public void editarImovel() {
		try {
			ImovelDAO dao = new ImovelDAO();
			//dao.atualizar(imovel);
			imoveis = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Im�vel " + imovel.getNome() + " atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Mensagem de erro: " + e.getMessage());
		}
	}
	
	
	public void excluirImovel() {
		try {
			ImovelDAO dao = new ImovelDAO();
			dao.excluir(imovel);
			imoveis = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Im�vel " + imovel.getNome() + " exclu�do com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Mensagem de erro: " + e.getMessage());
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
