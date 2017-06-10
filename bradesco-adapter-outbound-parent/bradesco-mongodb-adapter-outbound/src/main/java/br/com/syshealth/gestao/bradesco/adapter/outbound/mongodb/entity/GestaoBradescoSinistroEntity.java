package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.GsonBuilder;
import com.querydsl.core.annotations.QueryEntity;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto.SinistroDto;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
@QueryEntity
@Document(collection = "sinistro")
public class GestaoBradescoSinistroEntity {

    private String id;

    private SinistroDto sinistro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SinistroDto getSinistro() {
        return sinistro;
    }

    public void setSinistro(SinistroDto sinistro) {
        this.sinistro = sinistro;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(Sistema.FORMATO_DATA.getValue()).create().toJson(this);
    }

}
