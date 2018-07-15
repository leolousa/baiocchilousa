package br.com.baiocchilousa.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.baiocchilousa.algamoney.api.model.Categoria;
import br.com.baiocchilousa.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll(); 
    }
    
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        
        //Retorna no corpo da resposta a uri do novo recurso inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
            .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        
        return ResponseEntity.created(uri).body(categoriaSalva);
        
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findById(codigo).orElse(null);        
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();  
    }
    
}
