package br.com.baiocchilousa.algamoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.baiocchilousa.algamoney.api.event.RecursoCriadoEvent;

/**
 * Classe que monitora os eventos na aplicação. Quando lançarmos o RecursoCriadoEvent
 * é esta classe que irá escutar o evento.
 * @author leolo
 *
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

    @Override
    public void onApplicationEvent(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Long codigo = event.getCodigo();
        
        //Retorna no corpo da resposta a uri do novo recurso inserido - Header Location
        adicionaHeaderLocation(response, codigo);
    }

    private void adicionaHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
            .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}
