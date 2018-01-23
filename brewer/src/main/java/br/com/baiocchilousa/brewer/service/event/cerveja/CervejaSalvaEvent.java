package br.com.baiocchilousa.brewer.service.event.cerveja;

import org.springframework.util.StringUtils;

import br.com.baiocchilousa.brewer.model.Cerveja;

/**
 * Classe de evento quando uma cerveja for salvar
 * @author leolo
 *
 */
public class CervejaSalvaEvent {

	private Cerveja cerveja;

	public CervejaSalvaEvent(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	public Cerveja getCerveja() {
		return cerveja;
	}
	
	public boolean temFoto() {
		 return !StringUtils.isEmpty(cerveja.getFoto());
	}
	
	
}
