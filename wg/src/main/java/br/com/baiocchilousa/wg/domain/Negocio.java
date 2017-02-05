package br.com.baiocchilousa.wg.domain;

import java.math.BigDecimal;
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
public class Negocio extends GenericDomain {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Imovel imovel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private TipoNegocio tipoNegocio;

	@Column(nullable = false)
	private Boolean ativo;

	@Column(length = 80, nullable = false)
	private String titulo;

	@Column(columnDefinition = "mediumtext")
	private String descricao;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal valor;

	private Boolean destaque;

	private String email;

	@Column(nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataPublicacao;

	@Column(nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date tsRegistro;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuarioRegistro;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date tsAtualizacao;

	@ManyToOne
	private Usuario usuarioAtualizacao;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public TipoNegocio getTipoNegocio() {
		return tipoNegocio;
	}

	public void setTipoNegocio(TipoNegocio tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
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
