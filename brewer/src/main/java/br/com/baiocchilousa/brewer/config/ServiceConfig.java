package br.com.baiocchilousa.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.baiocchilousa.brewer.service.CadastroCervejaService;
import br.com.baiocchilousa.brewer.storage.FotoStorage;
import br.com.baiocchilousa.brewer.storage.local.FotoStorageLocal;



@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {

	
	//Método que implementa o armazenamento de fotos, neste ponto podemos alterar
	//a implementação para Amazon S3, por exemplo.
	@Bean
	public FotoStorage fotoStorage(){
		return new FotoStorageLocal();
	}
}
