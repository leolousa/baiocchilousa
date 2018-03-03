package br.com.baiocchilousa.brewer.config.format;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Classe de configuração para LocalDate
 * Podemos especificar uma propriedade no application.properties
 * para o formato de cada locale (ex.: localtime.format-pt_BR=dd/MM/yyyy)
 */
@Component
public class LocalDateFormatter extends TemporalFormatter<LocalDate> {

	@Autowired
	private Environment env;
	

	@Override
	public String pattern(Locale locale) {
		return env.getProperty("localdate.format-" + locale, "dd/MM/yyyy");
	}

	@Override
	public LocalDate parse(String text, DateTimeFormatter formatter) {
		return LocalDate.parse(text, formatter);
	}



}
