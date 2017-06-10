package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.DadosBancarios;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Dependente;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Titular;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.TitularComplemento;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Trailler;
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

    public static DateFormatPattern getDateFormatPattern(java.lang.reflect.Field f) {
        return f.getAnnotation(DateFormatPattern.class);
    }

    public static Header getHeader(java.lang.reflect.Field f) {
        return f.getAnnotation(Header.class);
    }

    public static boolean isHeader(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Header.class);
    }

    public static Titular getTitular(java.lang.reflect.Field f) {
        return f.getAnnotation(Titular.class);
    }

    public static boolean isTitular(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Titular.class);
    }

    public static TitularComplemento getTitularComplemento(java.lang.reflect.Field f) {
        return f.getAnnotation(TitularComplemento.class);
    }

    public static boolean isTitularComplemento(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(TitularComplemento.class);
    }

    public static DadosBancarios getDadosBancarios(java.lang.reflect.Field f) {
        return f.getAnnotation(DadosBancarios.class);
    }

    public static boolean isDadosBancarios(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(DadosBancarios.class);
    }

    public static Dependente getDependente(java.lang.reflect.Field f) {
        return f.getAnnotation(Dependente.class);
    }

    public static boolean isDependente(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Dependente.class);
    }

    public static Trailler getTrailler(java.lang.reflect.Field f) {
        return f.getAnnotation(Trailler.class);
    }

    public static boolean isTrailler(java.lang.reflect.Field f) {
        return f.isAnnotationPresent(Trailler.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassToTitular(Class<T> c) {
        Class<T> cl = c;
        if (isTitularList(cl)) {
            cl = getClassToList(getFieldTitular(cl));
        }
        return cl;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassToDependente(Class<T> c) {
        Class<T> cl = c;
        if (isDependenteList(cl)) {
            cl = getClassToList(getFieldDependente(cl));
        }
        return cl;
    }

    @SuppressWarnings("rawtypes")
    public static Class getClassToList(java.lang.reflect.Field field) {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        return (Class) pt.getActualTypeArguments()[0];
    }

    public static <T> boolean isTitularList(Class<T> c) {
        for (java.lang.reflect.Field f : getFields(c)) {
            if (isTitular(f) && List.class.isAssignableFrom(f.getType())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isDependenteList(Class<T> c) {
        for (java.lang.reflect.Field f : getFields(c)) {
            if (isDependente(f) && List.class.isAssignableFrom(f.getType())) {
                return true;
            }
        }
        return false;
    }

    public static <T> java.lang.reflect.Field getFieldTitular(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isTitular(f) && List.class.isAssignableFrom(f.getType())) {
                return f;
            }
        }
        return null;
    }

    public static <T> java.lang.reflect.Field getFieldDependente(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isDependente(f) && List.class.isAssignableFrom(f.getType())) {
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

    public static <T> boolean isFieldTitular(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isTitular(f) && List.class.isAssignableFrom(f.getType())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isFieldTitularComplemento(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isTitularComplemento(f) && List.class.isAssignableFrom(f.getType())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isFieldDadosBancarios(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isDadosBancarios(f) && List.class.isAssignableFrom(f.getType())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isFieldDependente(Class<T> cl) {
        for (java.lang.reflect.Field f : getFields(cl)) {
            if (isDependente(f) && List.class.isAssignableFrom(f.getType())) {
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
