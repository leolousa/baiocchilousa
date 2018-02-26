package br.com.baiocchilousa.brewer.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.storage.FotoStorage;
/**
 * Classe para controlar as url das fotos quando o objeto Cerveja for carregado.
 * @author leolo
 *
 */
public class CervejaEntityListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@PostLoad
	public void postLoad(final Cerveja cerveja) {
		//Ajuda a injetar os beans necessários baseado no contexto vigente 'this'
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
	}
}
