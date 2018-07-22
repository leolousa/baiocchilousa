package br.com.baiocchilousa.algamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.algamoney.api.model.Lancamento;
import br.com.baiocchilousa.algamoney.api.model.Pessoa;
import br.com.baiocchilousa.algamoney.api.repository.LancamentoRepository;
import br.com.baiocchilousa.algamoney.api.repository.PessoaRepository;
import br.com.baiocchilousa.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

/**
 * Classe de serviços da entidade Lancamento para
 * separar as regras de negócio da classe de controle
 * @author leolo
 *
 */
@Service
public class LancamentoService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(@Valid Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
        if(pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

}
