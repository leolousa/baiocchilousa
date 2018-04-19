package br.com.baiocchilousa.brewer.config;

import javax.cache.Caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching //Habilita a implementação do cache no Spring
@EnableAsync //Habilita os métodos assíncronos
public class WebConfig implements WebMvcConfigurer{

	
	/**
	 * Método que configura o cache da aplicação especificado pelo JCache e implementado
	 * com EhCahe - Usado no carregamento do combo de cidades no cadastro de clientes.
	 * @return JCacheCacheManager
	 * @throws Exception
	 */
	@Bean
	public CacheManager cacheManager() throws Exception {
		return new JCacheCacheManager(Caching.getCachingProvider().getCacheManager(
				getClass().getResource("/env/ehcache.xml").toURI(), 
				getClass().getClassLoader()));
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/webjars/**")
          .addResourceLocations("/webjars/");
    }
	
//	//Métodos para trocar o idioma dinamicamente armazenando em Cookie é necessário criar o link passando o locale ex: locale=en_US
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	  registry.addInterceptor(new LocaleChangeInterceptor());
//	}

//	@Bean
//	public LocaleResolver localeResolver(){
//	  return new CookieLocaleResolver();
//	}
}
