package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.enuns.Align;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Titular {

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
    int begin() default 0;

    /**
     * End.
     *
     * @return the int
     */
    int end() default 0;

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
