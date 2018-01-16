package br.com.baiocchilousa.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp="([a-zA-Z]{2}\\d{4})?")
public @interface SKU {
	/**
	 * Annotation personalizada para validação do campo SKU
	 * @return
	 */
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "SKU deve seguir o padrão XX9999";

	Class<?>[] groups() default {};//Propriedade para agrupar validações
	Class<? extends Payload>[] payload() default {};//Propriedades para carregar mais informações da anotação
}
