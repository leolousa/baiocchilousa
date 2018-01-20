package br.com.baiocchilousa.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface de implementação do armazenamento de fotos do sistema
 * @author leolo
 *
 */
public interface FotoStorage {
	
	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);
	
}
