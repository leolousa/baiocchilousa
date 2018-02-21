package br.com.baiocchilousa.brewer.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.baiocchilousa.brewer.storage.FotoStorage;


/**
 * Classe que executa metodos caso o evento definido seja executado
 * @author leolo
 *
 */
@Component
public class CervejaListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")//Condição para executar a anotação do listener deste metodo
	public void cervejaSalva(CervejaSalvaEvent evento) {
		fotoStorage.salvar(evento.getCerveja().getFoto());
	}
}
