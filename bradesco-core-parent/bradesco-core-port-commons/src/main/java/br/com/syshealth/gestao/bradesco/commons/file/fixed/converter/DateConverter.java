package br.com.syshealth.gestao.bradesco.commons.file.fixed.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * The Class DoubleConverter.
 *
 * @author Danilo.Rubervany
 */
public class DateConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Date) {
			return (T) value;
		}
		if (value instanceof String) {
			try {

				SimpleDateFormat dateFormatSistema = new SimpleDateFormat("dd-mm-yyyy");
				return (T) dateFormatSistema.parse((String) value);
			} catch (Exception e) {
				throw new ConversionException("Falha na Conversao do " + value.getClass().getName() + " para "
						+ type.getName() + " valor " + value, e);
			}
		}
		throw new ConversionException(
				"Falha na Conversao do " + value.getClass().getName() + " para " + type.getName() + " valor " + value);
	}

}
