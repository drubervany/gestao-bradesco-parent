package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.enuns;

import org.apache.commons.lang3.StringUtils;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.Field;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.Format;

/**
 * The Enum Align.
 *
 * @author Danilo.Rubervany
 */
public enum Align implements Format {

    /** The left. */
    LEFT {

        public String format(String s, int size, char padding) {
            return StringUtils.rightPad(s, size, padding);
        }
    },

    /** The rigth. */
    RIGHT {

        public String format(String s, int size, char padding) {
            return StringUtils.leftPad(s, size, padding);
        }
    };

    /**
     * Format.
     *
     * @param value
     *            the value
     * @param field
     *            the field
     * @return the string
     */
    public String formatter(Object value, final Field field) {
        return format(value, field);
    }

}
