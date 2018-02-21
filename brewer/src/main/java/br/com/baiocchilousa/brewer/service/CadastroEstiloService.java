package br.com.baiocchilousa.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.Estilo;
import br.com.baiocchilousa.brewer.repository.EstiloRepository;
import br.com.baiocchilousa.brewer.service.exception.NomeEstiloJaCadastradoException;

/**
 * Classe de serviços (regras de negócio costumam ficar nesta classe) 
 * @author leolo
 *
 */
@Service
public class CadastroEstiloService {

	@Autowired
	private EstiloRepository estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo){
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		if(estiloOptional.isPresent()){
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		return estilos.saveAndFlush(estilo);
	}
}
