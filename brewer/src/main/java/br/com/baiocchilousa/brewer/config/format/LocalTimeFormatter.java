package br.com.baiocchilousa.brewer.config.format;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Classe de configuração para LocalTime
 * Podemos especificar uma propriedade no application.properties
 * para o formato de cada locale (ex.: localtime.format-pt_BR=HH:mm)
 */
@Component
public class LocalTimeFormatter extends TemporalFormatter<LocalTime> {

	@Autowired
	private Environment env;
	
	
	@Override
	public String pattern(Locale locale) {
		return env.getProperty("localdate.format-" + locale, "HH:mm");
	}

	@Override
	public LocalTime parse(String text, DateTimeFormatter formatter) {
		return LocalTime.parse(text, formatter);
	}



}
