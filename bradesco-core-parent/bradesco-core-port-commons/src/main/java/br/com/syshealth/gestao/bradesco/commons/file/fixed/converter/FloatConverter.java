package br.com.syshealth.gestao.bradesco.commons.file.fixed.converter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class FloatConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if(value == null) {
			return null;
		}
		if(value instanceof Float) {
			return (T) value;
		}
		if(value instanceof String) {
			try {
				String v = value.toString();
				StringBuilder sb = new StringBuilder(value.toString());
				if (v.length() == 12) {
					sb.insert(10, '.');
				}
				if (v.length() == 8) {
					sb.insert(6, '.');
				}
				return (T) new Float(sb.toString());
			} catch (Exception e) {
				throw new ConversionException("Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value, e);
			}
		}
		throw new ConversionException(
				"Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value);
	}

}
