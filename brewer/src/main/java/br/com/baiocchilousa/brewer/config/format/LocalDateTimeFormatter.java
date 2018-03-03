package br.com.baiocchilousa.brewer.config.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeFormatter extends TemporalFormatter<LocalDateTime> {

	@Autowired
	private Environment env;
	
	/**
	 * Classe de configuração para LocalDateTime
	 * Podemos especificar uma propriedade no application.properties
	 * para o formato de cada locale (ex.: localtime.format-pt_BR=dd/MM/yyyy HH:mm)
	 */
	@Override
	public String pattern(Locale locale) {
		return env.getProperty("localdate.format-" + locale, "dd/MM/yyyy HH:mm");
	}

	@Override
	public LocalDateTime parse(String text, DateTimeFormatter formatter) {
		return LocalDateTime.parse(text, formatter);
	}



}
