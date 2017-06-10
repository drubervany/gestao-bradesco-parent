package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro;

import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getClassToMovimento;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getDateFormatPattern;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getFields;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getHeader;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getTitularDependente;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.getTrailler;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.isDateFormatPattern;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.isHeader;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.isMovimentoList;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.isTitularDependente;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.isTrailler;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.newInstance;
import static br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.utils.ReflectionUtils.set;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.converter.DateConverter;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.converter.DoubleConverter;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.converter.IntegerConverter;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.converter.LongConverter;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.ConvertTypeException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.LineSizeException;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.Field.TypeLine;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Movimento;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Trailler;

/**
 * The Class FixedFormat.
 *
 * @author Danilo.Rubervany
 */
public class FixedFormat {

    /** The head size. */
    private int headerSize;

    /** The body size. */
    private int movimentoSize;

    /** The foot size. */
    private int traillerSize;

    public <T> T readLines(String l, Class<T> clazz) {
        T t = newInstance(clazz);
        readLine(t, l, clazz);
        return t;
    }

    /**
     * Read line.
     *
     * @param <T>
     *            the generic type
     * @param t
     *            the t
     * @param s
     *            the s
     * @param c
     *            the c
     */
    private <T> void readLine(T t, String s, Class<?> c) {
        getTemplate(c).stream().filter(f -> isPrefix(s, f.getPrefix())).forEach(f -> {
            String x = null;
            if (s.length() < f.getSize() || s.length() > f.getSize()) {
                throw new LineSizeException(f.getSize(), s.length(), f.getPrefix());
            }
            try {
                x = s.substring(f.getPrefix().length());
                x = x.substring(f.getBeginIndex(), f.getEndIndex()).trim();

                if (f.getDatePattern() != null) {
                    if ("00000000".equals(x))
                        x = null;
                    else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(f.getDatePattern());
                        Date data = dateFormat.parse(x);

                        SimpleDateFormat dateFormatSistema = new SimpleDateFormat(Sistema.FORMATO_DATA.getValue());
                        x = dateFormatSistema.format(data);
                    }
                }

                Object value = convert(x, f.getType());
                set(t, f.getName(), value);
            } catch (StringIndexOutOfBoundsException e) {
                FixedFormatException fe = new FixedFormatException("Falha na Recuperacao do Campo [name:" + f.getName()
                        + ", begin:" + f.getBeginIndex() + ", end:" + f.getEndIndex() + "]", e);
                fe.setName(f.getName());
                throw fe;
            } catch (ConversionException e) {
                ConvertTypeException ct = new ConvertTypeException(e);
                ct.setName(f.getName());
                ct.setValue(x);
                throw ct;
            } catch (ParseException e) {
                ConvertTypeException ct = new ConvertTypeException(e);
                ct.setName(f.getName());
                ct.setValue(x);
                throw ct;
            }
        });
    }

    /**
     * Gets the template.
     *
     * @param <T>
     *            the generic type
     * @param c
     *            the c
     * @return the template
     */
    public <T> List<Field> getTemplate(Class<T> c) {
        List<Field> fields = new ArrayList<>();
        for (java.lang.reflect.Field f : getFields(c)) {
            Field field = new Field();
            field.setClazz(c);

            if (isHeader(f)) {
                Header p = getHeader(f);
                field.setTypeLine(TypeLine.HEADER);
                field.setAlign(p.align());
                field.setBeginIndex(p.begin());
                field.setEndIndex(p.end());
                field.setName(f.getName());
                field.setPadding(p.padding());
                field.setPrefix(p.prefix());
                field.setType(f.getType());
                field.setSize(headerSize);
            } else if (isTitularDependente(f)) {
                Movimento td = getTitularDependente(f);
                field.setTypeLine(TypeLine.MOVIMENTO);
                field.setAlign(td.align());
                field.setBeginIndex(td.begin());
                field.setEndIndex(td.end());
                field.setName(f.getName());
                field.setPadding(td.padding());
                field.setPrefix(td.prefix());
                field.setType(f.getType());
                field.setSize(movimentoSize);
                if (isMovimentoList(c)) {
                    Class<T> cb = getClassToMovimento(c);
                    field.getColumns().addAll(getTemplate(cb));
                }
            } else if (isTrailler(f)) {
                Trailler p = getTrailler(f);
                field.setTypeLine(TypeLine.TRAILLER);
                field.setAlign(p.align());
                field.setBeginIndex(p.begin());
                field.setEndIndex(p.end());
                field.setName(f.getName());
                field.setPadding(p.padding());
                field.setPrefix(p.prefix());
                field.setType(f.getType());
                field.setSize(traillerSize);
            }

            if (isDateFormatPattern(f)) {
                DateFormatPattern p = getDateFormatPattern(f);
                field.setDatePattern(p.value());
            }
            fields.add(field);
        }
        return fields;
    }

    /**
     * Sets the head size.
     *
     * @param headSize
     *            the new head size
     */
    public void setHeaderSize(int headSize) {
        this.headerSize = headSize;
    }

    /**
     * Sets the body size.
     *
     * @param movimentoSize
     *            the new body size
     */
    public void setMovimentoSize(int movimentoSize) {
        this.movimentoSize = movimentoSize;
    }

    /**
     * Sets the foot size.
     *
     * @param traillerSize
     *            the new foot size
     */
    public void setTraillerSize(int traillerSize) {
        this.traillerSize = traillerSize;
    }

    /**
     * Checks if is prefix.
     *
     * @param s
     *            the s
     * @param p
     *            the p
     * @return true, if is prefix
     */
    private boolean isPrefix(String s, String p) {
        return isNotBlank(p) && isNotBlank(s) && s.startsWith(p);
    }

    /**
     * Converte Tipo de Dado.
     *
     * @param x
     *            Valor a ser convertido
     * @param type
     *            Tipo para convers�o
     * @return Object do resultado
     */
    private Object convert(String x, Class<?> type) {
        ConvertUtils.register(new IntegerConverter(), Integer.class);
        ConvertUtils.register(new LongConverter(), Long.class);
        ConvertUtils.register(new DoubleConverter(), Double.class);
        ConvertUtils.register(new DateConverter(), Date.class);
        return ConvertUtils.convert(x, type);
    }

    /**
     * Checks if is not blank.
     *
     * @param str
     *            the str
     * @return true, if is not blank
     */
    private boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Checks if is blank.
     *
     * @param str
     *            the str
     * @return true, if is blank
     */
    private boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

}
