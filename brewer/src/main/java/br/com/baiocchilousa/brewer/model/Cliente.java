package br.com.baiocchilousa.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.baiocchilousa.brewer.model.validation.ClienteGroupSequenceProvider;
import br.com.baiocchilousa.brewer.model.validation.group.CnpjGroup;
import br.com.baiocchilousa.brewer.model.validation.group.CpfGroup;

@Entity
@Table(name="cliente")
// Para validação em grupo. Validar seguindo uma sequência (ex.: CPF/CNPJ)
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotNull(message = "Tipo pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class) 
	@Column(name = "cpf_cnpj")
	private String cpfOuCnpj;
	
	private String telefone;
	
	@Email(message = "E-mail inválido")
	private String email;
	
	@JsonIgnore// Para não inicializar o objeto endereço na pesquisa rápida de clientes
	@Embedded
	private Endereco endereco;

	//Antes de persistir ou atualizar remove os caracteres especiais
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		//Remove os caracteres especiais do campo
		this.cpfOuCnpj = TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}
	
	//Após carregar o objeto, formata a propriedade cpfOuCnpj
	@PostLoad
	private void postLoad() {
		this.cpfOuCnpj = this.tipoPessoa.formatar(this.cpfOuCnpj);
	}
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	//Método Get inserido para usar como busca de um cpfCnpj já cadastrado 
	public String getCpfOuCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
