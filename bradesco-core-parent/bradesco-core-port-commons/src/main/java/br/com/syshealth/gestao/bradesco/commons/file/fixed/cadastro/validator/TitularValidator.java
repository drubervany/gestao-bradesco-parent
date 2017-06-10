package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;

/**
 * The Interface HeaderSubFaturaValidator.
 *
 * @author Danilo.Rubervany
 * @param <T>
 *            the generic type
 */
@FunctionalInterface
public interface TitularValidator<T> {

	/**
	 * Validate.
	 *
	 * @param t
	 *            the t
	 * @throws FixedFormatException
	 *             the fixed format exception
	 */
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
