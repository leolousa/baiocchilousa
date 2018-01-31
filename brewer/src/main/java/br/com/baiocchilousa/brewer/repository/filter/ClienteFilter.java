package br.com.baiocchilousa.brewer.repository.filter;

/**
 * Objeto criado para pesquisa de clientes na página de pesquisa
 * @author leolo
 *
 */
public class ClienteFilter {

	private String nome;
	private String cpfOuCnpj;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	
	
}
