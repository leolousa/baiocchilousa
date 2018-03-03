package br.com.baiocchilousa.brewer.repository.listener;

import javax.persistence.PostLoad;

import br.com.baiocchilousa.brewer.BrewerApplication;
import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.storage.FotoStorage;
/**
 * Classe para controlar as url das fotos quando o objeto Cerveja for carregado.
 * @author leolo
 *
 */
public class CervejaEntityListener {
	
	@PostLoad
	public void postLoad(final Cerveja cerveja) {
		FotoStorage fotoStorage = BrewerApplication.getBean(FotoStorage.class);
		
		cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
	}
}
