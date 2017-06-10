package br.com.syshealth.gestao.bradesco.commons.file.fixed.converter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * The Class LongConverter.
 *
 * @author Danilo.Rubervany
 */
public class LongConverter implements Converter {

	/* (non-Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if(value == null) {
			return null;
		}
		if(value instanceof Long) {
			return (T) value;
		}
		if(value instanceof String) {
			try {
				String v = value.toString();
				return (T) new Long(v);
			} catch (Exception e) {
				throw new ConversionException("Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value, e);
			}
		}
		throw new ConversionException(
				"Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value);
	}

}
