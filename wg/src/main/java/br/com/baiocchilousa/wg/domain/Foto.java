package br.com.baiocchilousa.wg.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Foto extends GenericDomain{


	@Column(nullable = false)
	private Boolean ativo;
	
	@Column (length = 50, nullable = false)
	private String titulo;
	
	@Column (length = 255)
	private String descricao;
	
	@Column (length = 150, nullable = false)
	private String nomeArquivo;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date tsRegistro;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Usuario usuarioRegistro;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date tsAtualizacao;
	
	@ManyToOne
	private Usuario usuarioAtualizacao;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getTsRegistro() {
		return tsRegistro;
	}

	public void setTsRegistro(Date tsRegistro) {
		this.tsRegistro = tsRegistro;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Date getTsAtualizacao() {
		return tsAtualizacao;
	}

	public void setTsAtualizacao(Date tsAtualizacao) {
		this.tsAtualizacao = tsAtualizacao;
	}

	public Usuario getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}


}
