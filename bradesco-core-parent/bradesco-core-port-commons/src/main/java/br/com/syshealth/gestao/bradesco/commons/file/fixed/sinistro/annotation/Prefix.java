package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.enuns.PrefixType;

/**
 * The Interface Prefix.
 *
 * @author Danilo.Rubervany
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {

    /**
     * Prefix type.
     *
     * @return the prefix type
     */
    PrefixType prefixType();

}
