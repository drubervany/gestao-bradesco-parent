package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;

/**
 * 
 * @author Danilo.Rubervany
 *
 * @param <T>
 */
@FunctionalInterface
public interface TitularComplementoValidator<T> {
	public void validate(T t) throws FixedFormatException;

	/**
	 * Validate.
	 *
	 * @param constraints
	 *            the constraints
	 */
	default void validate(Set<ConstraintViolation<T>> constraints) {

	}
}
