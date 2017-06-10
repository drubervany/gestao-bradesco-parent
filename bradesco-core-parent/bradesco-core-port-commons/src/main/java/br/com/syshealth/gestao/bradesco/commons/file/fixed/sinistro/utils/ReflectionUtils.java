package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Movimento;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Trailler;

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

    public static DateFormatPattern getDateFormatPattern(java.lang.reflect.Field f) {
        return f.getAnnotation(DateFormatPattern.class);
    }

    public static Movimento getTitularDependente(java.lang.reflect.Field f) {
        return f.getAnnotation(Movimento.class);
    }

    public static boolean isTitularDependente(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Movimento.class);
    }

    public static Trailler getTrailler(java.lang.reflect.Field f) {
        return f.getAnnotation(Trailler.class);
    }

    public static boolean isTrailler(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Trailler.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassToMovimento(Class<T> c) {
        Class<T> cl = c;
        if (isMovimentoList(cl)) {
            cl = getClassToList(getFieldTitularDependente(cl));
        }
        return cl;
    }

    @SuppressWarnings("rawtypes")
    public static Class getClassToList(java.lang.reflect.Field field) {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        return (Class) pt.getActualTypeArguments()[0];
    }

    public static <T> boolean isMovimentoList(Class<T> c) {
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

    public static <T> boolean isFieldTitularDependente(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isTitularDependente(f) && List.class.isAssignableFrom(f.getType())) {
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
