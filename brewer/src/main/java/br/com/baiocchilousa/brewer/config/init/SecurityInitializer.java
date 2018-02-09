package br.com.baiocchilousa.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
/**
 * Classe de inicialização/configuração do Spring Secutity
 * @author leolo
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	//Configurações de encoding para as requisições quando passamos a utilizar o filtro para segurança
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		
		//Configura o tempo de sessão em segundos - Sempre expira após o período definido!
		//Usar o web.xml para configurar a sessão inativa!
		//servletContext.getSessionCookieConfig().setMaxAge(20);

		//Usar a definição de sessão JSESSION_ID com COOKIES
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
		
		
		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forcEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
