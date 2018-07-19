package br.com.baiocchilousa.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.algamoney.api.model.Pessoa;
import br.com.baiocchilousa.algamoney.api.repository.PessoaRepository;
/**
 * Classe de serviços da Pessoa, para isolar as regras de negócio
 * 
 * @author leolo
 *
 */
@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }
    
    private Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
        if(!pessoa.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        Pessoa pessoaAlterada = pessoa.get();
        
        //Copia pessoa para pessoaSalva excetuando o código
        BeanUtils.copyProperties(pessoa, pessoaAlterada, "codigo");
        
        return pessoaAlterada;
    }
}
