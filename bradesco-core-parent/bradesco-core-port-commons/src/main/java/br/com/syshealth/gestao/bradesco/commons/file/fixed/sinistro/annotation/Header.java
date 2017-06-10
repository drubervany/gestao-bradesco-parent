package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.enuns.Align;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Header {

    /**
     * Prefix.
     *
     * @return the string
     */
    String prefix();

    /**
     * Begin.
     *
     * @return the int
     */
    int begin();

    /**
     * End.
     *
     * @return the int
     */
    int end();

    /**
     * Align.
     *
     * @return the align
     */
    Align align() default Align.RIGHT;

    /**
     * Padding.
     *
     * @return the char
     */
    char padding() default ' ';

}
