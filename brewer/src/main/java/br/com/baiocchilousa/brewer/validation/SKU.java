package br.com.baiocchilousa.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * Annotation personalizada para validação do campo SKU
 * @return
 */

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp="([a-zA-Z]{2}\\d{4})?")
public @interface SKU {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.com.baiocchilousa.constraints.SKU.message}";// Indica a mensagem a ser apresentada descrita no arquivo message.properties

	Class<?>[] groups() default {};//Propriedade para agrupar validações
	Class<? extends Payload>[] payload() default {};//Propriedades para carregar mais informações da anotação
}
