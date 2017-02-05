package br.com.baiocchilousa.wg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class TipoNegocio extends GenericDomain {

	@Column(nullable = false)
	private Boolean ativo;

	@Column(length = 50, nullable = false)
	private String nome;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
