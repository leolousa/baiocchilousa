package br.com.baiocchilousa.brewer.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import com.google.common.cache.CacheBuilder;

import br.com.baiocchilousa.brewer.config.format.BigDecimalFormatter;
import br.com.baiocchilousa.brewer.controller.CervejasController;
import br.com.baiocchilousa.brewer.controller.converter.CidadeConverter;
import br.com.baiocchilousa.brewer.controller.converter.EstadoConverter;
import br.com.baiocchilousa.brewer.controller.converter.EstiloConverter;
import br.com.baiocchilousa.brewer.controller.converter.GrupoConverter;
import br.com.baiocchilousa.brewer.session.TabelasItensSession;
import br.com.baiocchilousa.brewer.thymeleaf.BrewerDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { CervejasController.class, TabelasItensSession.class })
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableCaching //Habilita a implementação do cache no Spring
@EnableAsync //Habilita os métodos assíncronos
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		
		//Dialeto do Thymeleaf
		engine.addDialect(new LayoutDialect());
		
		//Dialeto customizado Brewer
		engine.addDialect(new BrewerDialect());
		
		//Dialeto do data extra attribute
		engine.addDialect(new DataAttributeDialect());
		
		//Dialeto do spring secutity
		engine.addDialect(new SpringSecurityDialect());
		
		return engine;
	}
	
	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	//Método que disponibiliza o Converter para uso na aplicação
	@Bean
	public FormattingConversionService mvcConversionService(){
		
		//Converter
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		//Formata a classe Estilo
		conversionService.addConverter(new EstiloConverter());
		
		//Formata a classe Cidade
		conversionService.addConverter(new CidadeConverter());
		
		//Formata a classe Estado
		conversionService.addConverter(new EstadoConverter());

		//Formata a classe Grupo
		conversionService.addConverter(new GrupoConverter());
		
		//Formata o bigdecimal para todo o sistema - Problemas quando temos locales diferentes
		//NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		
		//Formata o inteiro para todo o sistema - Problemas quando temos locales diferentes
		//NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");

		//Formata o bigdecimal com um formatador customizado
		BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter("#,##0.00");
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
		//Formata o integer com um formatador customizado o próprio BigDecimalFormatter
		BigDecimalFormatter integerFormatter = new BigDecimalFormatter("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
		
		//Formata a data para dd/MM/yyyy - Brasil - API de Datas do Java 8
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(conversionService);
		
		return conversionService;
	}

	//Fixa o português como sendo o locale principal
//	@Bean
//	public LocaleResolver localeResolver(){
//		return new FixedLocaleResolver(new Locale("pt", "BR"));
//	}
	
	@Bean
	public CacheManager cacheManager() {
		
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
				.maximumSize(3) // Número de Entradas no cache
				.expireAfterAccess(20, TimeUnit.SECONDS); //Tempo de expiração do cache
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8"); //Lista de caracteres acentuados:  http://www.utf8-chartable.de/
		return bundle;
	}
	
	//Para poder, nos controllers, enviar o objeto inteiro sem precisar dar um findOne com o código da entidade 
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter() {
		return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
	}
	
	//Bean criado para passar a localização dos arquivos de tradução messages.properties
	@Bean
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	    validatorFactoryBean.setValidationMessageSource(messageSource());
	    
	    return validatorFactoryBean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}
	
	
	//Métodos para trocar o idioma dinamicamente armazenando em Cookie é necessário criar o link passando o locale ex: locale=en_US
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver(){
	  return new CookieLocaleResolver();
	}
}
