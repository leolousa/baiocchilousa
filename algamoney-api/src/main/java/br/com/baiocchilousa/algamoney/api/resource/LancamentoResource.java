package br.com.baiocchilousa.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baiocchilousa.algamoney.api.event.RecursoCriadoEvent;
import br.com.baiocchilousa.algamoney.api.model.Categoria;
import br.com.baiocchilousa.algamoney.api.model.Lancamento;
import br.com.baiocchilousa.algamoney.api.model.Pessoa;
import br.com.baiocchilousa.algamoney.api.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
    
    @Autowired
    LancamentoRepository lancamentoRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @GetMapping
    public List<Lancamento> listar() {
        return lancamentoRepository.findAll(); 
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamento = lancamentoRepository.findById(codigo).orElse(null);        
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();  
    }
    
    @PostMapping
    public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

        //Evento para inclusão do Header Location
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }
}
