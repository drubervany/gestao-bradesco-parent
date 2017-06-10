package br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// TODO: Auto-generated Javadoc
/**
 * The Interface DateFormatPattern.
 * 
 * @author Danilo.Rubervany
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface DateFormatPattern {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value();
}