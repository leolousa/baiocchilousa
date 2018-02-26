package br.com.baiocchilousa.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.baiocchilousa.brewer.service.CadastroCervejaService;
import br.com.baiocchilousa.brewer.storage.FotoStorage;



@Configuration
@ComponentScan(basePackageClasses = { CadastroCervejaService.class, FotoStorage.class })
public class ServiceConfig {

}
