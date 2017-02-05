package br.com.baiocchilousa.wg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Uf extends GenericDomain{

	@Column(length = 2, nullable = false)
	private String sigla;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public Pais pais;
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
