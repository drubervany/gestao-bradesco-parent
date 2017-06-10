package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.GsonBuilder;
import com.querydsl.core.annotations.QueryEntity;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto.PremioDto;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
@QueryEntity
@Document(collection = "premio")
public class GestaoBradescoFaturaEntity {

    private String id;

    private PremioDto premio;

    public PremioDto getPremio() {
        return premio;
    }

    public void setPremio(PremioDto premio) {
        this.premio = premio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(Sistema.FORMATO_DATA.getValue()).create().toJson(this);
    }
}
