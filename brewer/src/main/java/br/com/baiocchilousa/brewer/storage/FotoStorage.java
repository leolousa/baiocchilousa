package br.com.baiocchilousa.brewer.storage;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface de implementação do armazenamento de fotos do sistema
 * @author leolo
 *
 */
public interface FotoStorage {
	
	public final String THUMBNAIL_PREFIX = "thumbnail."; 
	
	public String salvar(MultipartFile[] files);
	
	public byte[] recuperar(String foto);
	
	public byte[] recuperarThumbnail(String fotoCerveja);

	public void excluir(String foto);

	public String getUrl(String foto);
	
	default String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
}
