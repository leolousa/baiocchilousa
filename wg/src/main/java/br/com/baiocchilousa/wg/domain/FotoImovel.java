package br.com.baiocchilousa.wg.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class FotoImovel extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Foto foto;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Imovel imovel;

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

}
