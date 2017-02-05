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
public class Config extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String empresa;
	
	@Column(length = 255)
	private String endereco;
	
	@Column(length = 255)
	private String historico;
	
	@Column(length = 255)
	private String texto;
	
	@Column(length = 35)
	private String emailVenda;
	
	@Column(length = 35)
	private String emailAluguel;
	
	@Column(length = 35)
	private String emailAdm;
	
	@Column(length = 30)
	private String foneVenda;
	
	@Column(length = 30)
	private String foneAluguel;
	
	@Column(length = 30)
	private String rodape;


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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getEmailVenda() {
		return emailVenda;
	}

	public void setEmailVenda(String emailVenda) {
		this.emailVenda = emailVenda;
	}

	public String getEmailAluguel() {
		return emailAluguel;
	}

	public void setEmailAluguel(String emailAluguel) {
		this.emailAluguel = emailAluguel;
	}

	public String getEmailAdm() {
		return emailAdm;
	}

	public void setEmailAdm(String emailAdm) {
		this.emailAdm = emailAdm;
	}

	public String getFoneVenda() {
		return foneVenda;
	}

	public void setFoneVenda(String foneVenda) {
		this.foneVenda = foneVenda;
	}

	public String getFoneAluguel() {
		return foneAluguel;
	}

	public void setFoneAluguel(String foneAluguel) {
		this.foneAluguel = foneAluguel;
	}

	public String getRodape() {
		return rodape;
	}

	public void setRodape(String rodape) {
		this.rodape = rodape;
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
