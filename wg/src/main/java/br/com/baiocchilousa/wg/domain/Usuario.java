package br.com.baiocchilousa.wg.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private TipoUsuario tipo;

	private Boolean ativo;

	@Column(length = 30)
	private String apelido;

	@Column(length = 30, nullable = false)
	private String login;

	@Column(length = 32, nullable = false)
	private String senha;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date tsRegistro;


	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getTsRegistro() {
		return tsRegistro;
	}

	public void setTsRegistro(Date tsRegistro) {
		this.tsRegistro = tsRegistro;
	}

}
