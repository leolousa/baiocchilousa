	package br.com.baiocchilousa.brewer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.baiocchilousa.brewer.mail.Mailer;

/**
 * Clase que configura o Java Mail
 * Variável ambiente: ambiente
 * 
 * @author leolo
 *
 */
@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
//Seta o local da pasta do arquivo de propriedades com as configurações do e-mail - (se não informar default:'local').
//@PropertySource({ "classpath:env/mail-${ambiente:local}.properties" })

//${USERPROFILE}. Seta o local na Home do usuário da pasta do arquivo de propriedades
@PropertySource(value = { "file:\\${USERPROFILE}\\brewer\\.brewer-mail.properties" }, ignoreResourceNotFound = true)
public class MailConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setPassword(env.getProperty("password"));
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", false);
		props.put("mail.smtp.connectiontimeout", 10000);// (10s) miliseconds
		
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}
}
