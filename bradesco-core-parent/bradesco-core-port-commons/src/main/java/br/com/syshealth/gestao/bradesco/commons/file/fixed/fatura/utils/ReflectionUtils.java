package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.HeaderSubFatura;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TitularDependente;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.Trailler;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerObservacao;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerSubFatura;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;

public class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new FixedFormatException("Fail New Instance", e);
		}
	}

	public static <T> List<java.lang.reflect.Field> getFields(Class<T> c) {
		return FieldUtils.getAllFieldsList(c);
	}

	public static <T> List<java.lang.reflect.Field> getFields(Class<T> c, Class<? extends Annotation> a) {
		return FieldUtils.getFieldsListWithAnnotation(c, a);
	}

	public static Object get(Object o, String f) {
		Object v = null;
		try {
			v = FieldUtils.readDeclaredField(o, f, true);
		} catch (IllegalAccessException e) {
			throw new FixedFormatException("Fail Get Value", e);
		}
		return v;
	}

	public static boolean isDateFormatPattern(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(DateFormatPattern.class);
	}

	public static Header getHeader(java.lang.reflect.Field f) {
		return f.getAnnotation(Header.class);
	}

	public static boolean isHeader(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(Header.class);
	}

	public static HeaderSubFatura getHeaderSubFatura(java.lang.reflect.Field f) {
		return f.getAnnotation(HeaderSubFatura.class);
	}

	public static boolean isHeaderSubFatura(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(HeaderSubFatura.class);
	}

	public static DateFormatPattern getDateFormatPattern(java.lang.reflect.Field f) {
		return f.getAnnotation(DateFormatPattern.class);
	}

	public static TitularDependente getTitularDependente(java.lang.reflect.Field f) {
		return f.getAnnotation(TitularDependente.class);
	}

	public static boolean isTitularDependente(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(TitularDependente.class);
	}

	public static Trailler getTrailler(java.lang.reflect.Field f) {
		return f.getAnnotation(Trailler.class);
	}

	public static boolean isTrailler(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(Trailler.class);
	}

	public static TraillerSubFatura getTraillerSubFatura(java.lang.reflect.Field f) {
		return f.getAnnotation(TraillerSubFatura.class);
	}

	public static TraillerObservacao getTraillerObservacao(java.lang.reflect.Field f) {
		return f.getAnnotation(TraillerObservacao.class);
	}

	public static boolean isTraillerSubFatura(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(TraillerSubFatura.class);
	}

	public static boolean isTraillerObservacao(java.lang.reflect.Field f) {
		return f.isAnnotationPresent(TraillerObservacao.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassToTitularDependente(Class<T> c) {
		Class<T> cl = c;
		if (isTitularDependenteList(cl)) {
			cl = getClassToList(getFieldTitularDependente(cl));
		}
		return cl;
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassToHeaderSubFatura(Class<T> c) {
		Class<T> cl = c;
		if (isHeaderSubFaturaList(cl)) {
			cl = getClassToList(getFieldHeaderSubFatura(cl));
		}
		return cl;
	}

	@SuppressWarnings("rawtypes")
	public static Class getClassToList(java.lang.reflect.Field field) {
		ParameterizedType pt = (ParameterizedType) field.getGenericType();
		return (Class) pt.getActualTypeArguments()[0];
	}

	public static <T> boolean isHeaderSubFaturaList(Class<T> c) {
		for (java.lang.reflect.Field f : getFields(c)) {
			if (isHeaderSubFatura(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean isTitularDependenteList(Class<T> c) {
		for (java.lang.reflect.Field f : getFields(c)) {
			if (isTitularDependente(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> java.lang.reflect.Field getFieldTitularDependente(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isTitularDependente(f) && List.class.isAssignableFrom(f.getType())) {
				return f;
			}
		}
		return null;
	}

	public static <T> boolean isFieldHeader(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isHeader(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean isFieldHeaderSubFatura(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isHeaderSubFatura(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> java.lang.reflect.Field getFieldHeaderSubFatura(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isHeaderSubFatura(f) && List.class.isAssignableFrom(f.getType())) {
				return f;
			}
		}
		return null;
	}

	public static <T> boolean isFieldTitularDependente(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isTitularDependente(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean isFieldTraillerSubFatura(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isTraillerSubFatura(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean isFieldTrailler(Class<T> cl) {
		for (java.lang.reflect.Field f : getFields(cl)) {
			if (isTrailler(f) && List.class.isAssignableFrom(f.getType())) {
				return true;
			}
		}
		return false;
	}

	public static <T> void set(T t, String name, Object v) {
		try {
			FieldUtils.writeDeclaredField(t, name, v, true);
		} catch (IllegalAccessException e) {
			throw new FixedFormatException("Fail Set Value", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> void add(T t, String name, Object o) {
		List fields = (List) get(t, name);
		fields.add(o);
	}

}
