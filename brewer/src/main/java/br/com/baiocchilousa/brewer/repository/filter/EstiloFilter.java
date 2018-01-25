package br.com.baiocchilousa.brewer.repository.filter;

/**
 * Objeto criado para pesquisa de estilos na página de pesquisa
 * @author leolo
 *
 */
public class EstiloFilter {

	private String codigo;
	private String nome;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
