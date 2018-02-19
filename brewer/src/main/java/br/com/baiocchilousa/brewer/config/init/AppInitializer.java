package br.com.baiocchilousa.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.baiocchilousa.brewer.config.JPAConfig;
import br.com.baiocchilousa.brewer.config.MailConfig;
import br.com.baiocchilousa.brewer.config.SecurityConfig;
import br.com.baiocchilousa.brewer.config.ServiceConfig;
import br.com.baiocchilousa.brewer.config.WebConfig;
/**
 * Classe de inicialização da aplicação
 * @author leolo
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Mapeia as classes de configuração
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class, MailConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//Força o Encode para UTF-8
	@Override
	protected Filter[] getServletFilters() {
		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
		
		return new Filter[] { httpPutFormContentFilter };
	}
	
	@Override
	//Configura File/Multipart para ser enviado ao servidor
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
