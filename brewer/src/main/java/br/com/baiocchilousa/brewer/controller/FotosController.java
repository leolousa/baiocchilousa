package br.com.baiocchilousa.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.baiocchilousa.brewer.dto.FotoDTO;
import br.com.baiocchilousa.brewer.storage.FotoStorageRunnable;

/**
 * Classe que permite fazer o Upload de uma foto de maneira assincrona sem travar a Tread no servidor
 * @author leolo
 *
 */

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files){
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado));
		thread.start();
		
		
		return resultado;
	}
}
