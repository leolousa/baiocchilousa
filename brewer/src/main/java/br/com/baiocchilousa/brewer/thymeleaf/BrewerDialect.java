package br.com.baiocchilousa.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.baiocchilousa.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.baiocchilousa.brewer.thymeleaf.processor.MessageElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect{

	public BrewerDialect(){
		super("Baiocchi&Lousa Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}
		
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {	
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
