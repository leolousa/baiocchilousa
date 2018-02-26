package br.com.baiocchilousa.brewer.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.service.exception.ImpossivelExcluirEntidadeException;
import br.com.baiocchilousa.brewer.storage.FotoStorage;

/**
 * Classe de serviços (regras de negócio costumam ficar nesta classe)
 * 
 * @author leolo
 *
 */
@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejas;

	@Autowired
	private FotoStorage fotoStorage;

	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
	}

	@Transactional
	public void excluir(Cerveja cerveja) {
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
