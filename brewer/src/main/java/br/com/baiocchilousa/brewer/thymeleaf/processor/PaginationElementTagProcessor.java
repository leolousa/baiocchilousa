package br.com.baiocchilousa.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Classe que extende o Thymeleaf para criar um componente customizado (TAG HTML)
 * 
 * <brewer:pagination page="${pagina}" />
 * 
 * (Paginação de uma tabela HTML)
 *
 */
public class PaginationElementTagProcessor extends AbstractElementTagProcessor{

	private static final String NOME_TAG = "pagination";
	private static final int PRECEDENCIA = 1000;
	
	public PaginationElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();

		IAttribute page = tag.getAttribute("page");
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("fragments/paginacao :: pagination (%s)", page.getValue())));
		
		structureHandler.replaceWith(model, true);//Ainda é necessário que o Thymeleaf processe as suas tags (por isso o true)
	}

}
