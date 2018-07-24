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
import br.com.baiocchilousa.algamoney.api.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll(); 
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findOne(codigo);      
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();  
    }
    
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        
        //Evento para inclusão do Header Location
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
        
    }
    
    
}
