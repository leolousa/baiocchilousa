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
public class Pessoa extends GenericDomain {

	@Column(length = 80, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Character tipoPessoa; // F-Física - J-Jurídica

	private Long cpfCnpj;
	
	@Column(nullable = true)
	private int rgIe;

	private Date dataNascimento;

	@Column(length = 40, nullable = false)
	private String endereco;

	private Short numero;

	@Column(length = 30)
	private String bairro;

	@Column(length = 40)
	private String complemento;

	@Column(nullable = false)
	private int cep;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Cidade cidade;

	@Column(length = 14)
	private String foneFixo;

	@Column(length = 14)
	private String foneCel1;

	@Column(length = 14)
	private String foneCel2;

	@Column(length = 100)
	private String emailPrincipal;

	@Column(length = 100)
	private String emailAlternativo;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date tsRegistro;

	@Column(nullable = false)
	private Long usuarioRegistro;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date tsAtualizacao;

	private Long usuarioAtualizacao;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Character tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public int getRgIe() {
		return rgIe;
	}

	public void setRgIe(int rgIe) {
		this.rgIe = rgIe;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getFoneFixo() {
		return foneFixo;
	}

	public void setFoneFixo(String foneFixo) {
		this.foneFixo = foneFixo;
	}

	public String getFoneCel1() {
		return foneCel1;
	}

	public void setFoneCel1(String foneCel1) {
		this.foneCel1 = foneCel1;
	}

	public String getFoneCel2() {
		return foneCel2;
	}

	public void setFoneCel2(String foneCel2) {
		this.foneCel2 = foneCel2;
	}

	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getEmailAlternativo() {
		return emailAlternativo;
	}

	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}

	public Date getTsRegistro() {
		return tsRegistro;
	}

	public void setTsRegistro(Date tsRegistro) {
		this.tsRegistro = tsRegistro;
	}

	public Long getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Long usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Date getTsAtualizacao() {
		return tsAtualizacao;
	}

	public void setTsAtualizacao(Date tsAtualizacao) {
		this.tsAtualizacao = tsAtualizacao;
	}

	public Long getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(Long usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}


	
	
	

}
