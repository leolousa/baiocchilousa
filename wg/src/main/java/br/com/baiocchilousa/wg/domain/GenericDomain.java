package br.com.baiocchilousa.wg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Classe genérica para gerar a chave primária de uma entidade do Banco de Dados.
 * 
 * @param id   Campo chave primária da entidade
 * @author     Leonardo Baiocchi Lousa
 * @version    1.0
 */

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	//Método utilizado para montar os objetos e imprimir nos p:selectOneMenu
	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	
}
