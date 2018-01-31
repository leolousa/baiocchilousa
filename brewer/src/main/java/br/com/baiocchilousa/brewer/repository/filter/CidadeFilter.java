package br.com.baiocchilousa.brewer.repository.filter;

import br.com.baiocchilousa.brewer.model.Estado;

/**
 * Objeto criado para pesquisa de clientes na página de pesquisa
 * @author leolo
 *
 */
public class CidadeFilter {

	private String nome;
	private Estado estado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
