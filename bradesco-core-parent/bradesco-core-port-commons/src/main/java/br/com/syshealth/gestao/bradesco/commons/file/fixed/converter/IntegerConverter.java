package br.com.syshealth.gestao.bradesco.commons.file.fixed.converter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class IntegerConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if(value == null) {
			return null;
		}
		if(value instanceof Integer) {
			return (T) value;
		}
		if(value instanceof String) {
			try {
				String v = value.toString();
				return (T) new Integer(v);
			} catch (Exception e) {
				throw new ConversionException("Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value, e);
			}
		}
		throw new ConversionException(
				"Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value);
	}

}
