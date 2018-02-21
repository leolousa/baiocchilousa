package br.com.baiocchilousa.brewer.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.service.event.cerveja.CervejaSalvaEvent;
import br.com.baiocchilousa.brewer.service.exception.ImpossivelExcluirEntidadeException;
import br.com.baiocchilousa.brewer.storage.FotoStorage;

/**
 * Classe de serviços (regras de negócio costumam ficar nesta classe) 
 * @author leolo
 *
 */
@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Cerveja cerveja){
		cervejas.save(cerveja);
		
		//Publicamos o evento para avisar que a cerveja foi salva - desacoplamento do método!
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
		
		
	}

	@Transactional
	public void excluir(Cerveja cerveja){
			try {
				String foto = cerveja.getFoto();
				
				cervejas.delete(cerveja);
				cervejas.flush();
				fotoStorage.excluir(foto);
			} catch (PersistenceException e) {
				throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em alguma venda.");
			}
	}

}
