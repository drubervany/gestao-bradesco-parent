package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.utils.ReflectionUtils;

/**
 * The Interface Format.
 * 
 * @author Danilo.Rubervany
 */
@FunctionalInterface
public interface Format {

    abstract String format(String s, int size, char padding);

    /**
     * Format.
     *
     * @param value
     *            the value
     * @param field
     *            the field
     * @return the string
     */
    public default String format(Object value, final Field field) {
        value = ReflectionUtils.get(value, field.getName());
        String s = (value != null) ? value.toString() : "";
        int size = (field.getEndIndex() - field.getBeginIndex());
        s = format(s, size, field.getPadding());
        return s;
    }

}
