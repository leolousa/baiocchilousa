package br.com.baiocchilousa.brewer.repository.filter;

import java.util.List;

import br.com.baiocchilousa.brewer.model.Grupo;

/**
 * Objeto criado para pesquisa de clientes na página de pesquisa
 * @author leolo
 *
 */
public class UsuarioFilter {

	private String nome;
	private String email;
	private List<Grupo> grupos;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	
	
	
}
