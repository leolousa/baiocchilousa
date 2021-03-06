package br.com.baiocchilousa.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Classe que extende o Thymeleaf para criar um componente customizado (TAG HTML)
 * 
 * <brewer:message />
 * 
 * (Exibe mensagens na página HTML)
 *
 */

public class MessageElementTagProcessor extends AbstractElementTagProcessor{

	private static final String NOME_TAG = "message";
	private static final int PRECEDENCIA = 1000;
	
	public MessageElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		IModel model = modelFactory.createModel();
		
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/mensagensSucesso :: messageSuccess"));
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/mensagensErroValidacao :: messageError"));
		
		structureHandler.replaceWith(model, true);//Ainda é necessário que o Thymeleaf processe as suas tags (por isso o true)
	}

	
}
