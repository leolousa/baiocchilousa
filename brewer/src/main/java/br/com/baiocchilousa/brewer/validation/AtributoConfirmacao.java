package br.com.baiocchilousa.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import br.com.baiocchilousa.brewer.validation.validator.AtributoConfirmacaoValidator;

/**
 * Annotation personalizada para validar a confirmação de senha
 * @return
 */

@Target({ ElementType.TYPE })//Esta anotação só pode ser anotada em um Type (Classe)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class })// Classe que efetivamente faz a validação
public @interface AtributoConfirmacao {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não conferem";

	Class<?>[] groups() default {};//Propriedade para agrupar validações
	Class<? extends Payload>[] payload() default {};//Propriedades para carregar mais informações da anotação
	
	String atributo();
	
	String atributoConfirmacao();
}
