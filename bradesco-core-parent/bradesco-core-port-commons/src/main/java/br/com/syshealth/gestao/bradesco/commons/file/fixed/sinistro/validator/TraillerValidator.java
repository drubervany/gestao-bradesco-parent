package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.validator;

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
public interface TraillerValidator<T> {
	public void validate(T t) throws FixedFormatException;
	default void validate(Set<ConstraintViolation<T>> constraints) {
	}	
}
