package br.com.syshealth.gestao.bradesco.adapter.outbound.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.GsonBuilder;
import com.querydsl.core.annotations.QueryEntity;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto.CadastroDto;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
@QueryEntity
@Document(collection = "cadastro")
public class GestaoBradescoCadastroEntity {

    private String id;

    private CadastroDto cadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(Sistema.FORMATO_DATA.getValue()).create().toJson(this);
    }

    public CadastroDto getCadastro() {
        return cadastro;
    }

    public void setCadastro(CadastroDto cadastro) {
        this.cadastro = cadastro;
    }
}
