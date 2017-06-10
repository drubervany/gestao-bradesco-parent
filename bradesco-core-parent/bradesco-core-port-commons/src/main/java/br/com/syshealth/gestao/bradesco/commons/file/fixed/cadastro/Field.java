package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.enuns.Align;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.utils.ReflectionUtils;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.exception.FixedFormatException;

/**
 * @author Danilo.Rubervany
 */
public class Field {

    public enum TypeLine {
        HEADER,
        TITULAR,
        TITULAR_COMPLEMENTO,
        DADOS_BANCARIOS,
        DEPENDENTE,
        TRAILLER
    };

    private Class<?> clazz;

    private String name;

    private String prefix;

    private int beginIndex;

    private int endIndex;

    private Align align;

    private char padding;

    private Class<?> type;

    private List<Field> fields;

    private TypeLine typeLine;

    private int size;

    private String datePattern;

    public Field() {
        this.fields = new ArrayList<>();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public char getPadding() {
        return padding;
    }

    public void setPadding(char padding) {
        this.padding = padding;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public List<Field> getColumns() {
        return fields;
    }

    public void setColumns(List<Field> columns) {
        this.fields = columns;
    }

    public void add(Field column) {
        this.fields.add(column);
    }

    public TypeLine getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(TypeLine typeLine) {
        this.typeLine = typeLine;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPrefix(String s) {
        return s.startsWith(getPrefix());
    }

    public Field getFirst() {
        if (fields != null && fields.size() > 0) {
            return fields.get(0);
        }
        return null;
    }

    public Object get(Object o) {
        return ReflectionUtils.get(o, name);
    }

    public Object toConvert(String value) {
        return ConvertUtils.convert(value, getType());
    }

    public String asValue(String s) {
        String x = s;
        if (getPrefix() != null) {
            if (getPrefix().length() > 0) {
                x = s.substring(getPrefix().length());
            }
        }
        if (x.length() < size) {
            throw new FixedFormatException("Linha com tamanho menor que o suportado");
        }
        return x.substring(getBeginIndex(), getEndIndex());
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public String toString() {
        return "Field [clazz=" + clazz + ", name=" + name + ", prefix=" + prefix + ", beginIndex=" + beginIndex
                + ", endIndex=" + endIndex + ", align=" + align + ", padding=" + padding + ", type=" + type
                + ", fields=" + fields + ", typeLine=" + typeLine + ", size=" + size + "]";
    }
}
