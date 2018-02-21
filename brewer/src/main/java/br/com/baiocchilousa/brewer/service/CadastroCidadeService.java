package br.com.baiocchilousa.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.Cidade;
import br.com.baiocchilousa.brewer.repository.CidadeRepository;
import br.com.baiocchilousa.brewer.service.exception.CidadeJaCadastradaException;

/**
 * Classe de serviços (regras de negócio costumam ficar nesta classe) 
 * @author leolo
 *
 */
@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidades;
	
	
	@Transactional
	public void salvar(Cidade cidade){
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if(cidadeExistente.isPresent()) {
			throw new CidadeJaCadastradaException("Nome de cidade já cadastrado para este Estado!");
		}
		
		cidades.save(cidade);
	}
}
