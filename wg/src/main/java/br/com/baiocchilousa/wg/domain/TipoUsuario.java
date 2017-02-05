package br.com.baiocchilousa.wg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class TipoUsuario extends GenericDomain{


	@Column(length = 40, nullable = false)
	private String tipo;
	
	@Column(nullable = true)
	private Boolean incluir;
	
	@Column(nullable = true)
	private Boolean alterar;
	
	@Column(nullable = true)
	private Boolean excluir;
	
	@Column(nullable = true)
	private Boolean incluirUsuarios;

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getIncluir() {
		return incluir;
	}

	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}

	public Boolean getAlterar() {
		return alterar;
	}

	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}

	public Boolean getExcluir() {
		return excluir;
	}

	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}

	public Boolean getIncluirUsuarios() {
		return incluirUsuarios;
	}

	public void setIncluirUsuarios(Boolean incluirUsuarios) {
		this.incluirUsuarios = incluirUsuarios;
	}
	
	
	
}
