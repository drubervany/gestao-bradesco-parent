package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.GsonBuilder;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Titular;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Trailler;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
public class CadastroDto {

    @Header(prefix = "1", begin = 0, end = 17)
    private String tipoDeArquivo;

    @Header(prefix = "1", begin = 17, end = 23)
    private Long numeroDoContrato;

    @Header(prefix = "1", begin = 23, end = 29)
    @DateFormatPattern("yymmdd")
    private Date dataDeCompetencia_;

    @Header(prefix = "1", begin = 29, end = 35)
    @DateFormatPattern("yymmdd")
    private Date dataDeProcessamento_;

    @Header(prefix = "1", begin = 35, end = 41)
    private Integer dataDeCompetencia;

    @Header(prefix = "1", begin = 43, end = 59)
    private Integer dataDeProcessamento;

    @Header(prefix = "1", begin = 51, end = 179)
    private String filler;

    @Titular(prefix = "2")
    private List<TitularDto> titular;

    @Trailler(prefix = "4")
    private TraillerDto trailler;

    public String getTipoDeArquivo() {
        return tipoDeArquivo;
    }

    public void setTipoDeArquivo(String tipoDeArquivo) {
        this.tipoDeArquivo = tipoDeArquivo;
    }

    public Long getNumeroDoContrato() {
        return numeroDoContrato;
    }

    public void setNumeroDoContrato(Long numeroDoContrato) {
        this.numeroDoContrato = numeroDoContrato;
    }

    public Date getDataDeCompetencia_() {
        return dataDeCompetencia_;
    }

    public void setDataDeCompetencia_(Date dataDeCompetencia_) {
        this.dataDeCompetencia_ = dataDeCompetencia_;
    }

    public Date getDataDeProcessamento_() {
        return dataDeProcessamento_;
    }

    public void setDataDeProcessamento_(Date dataDeProcessamento_) {
        this.dataDeProcessamento_ = dataDeProcessamento_;
    }

    public Integer getDataDeCompetencia() {
        return dataDeCompetencia;
    }

    public void setDataDeCompetencia(Integer dataDeCompetencia) {
        this.dataDeCompetencia = dataDeCompetencia;
    }

    public Integer getDataDeProcessamento() {
        return dataDeProcessamento;
    }

    public void setDataDeProcessamento(Integer dataDeProcessamento) {
        this.dataDeProcessamento = dataDeProcessamento;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public List<TitularDto> getTitular() {
        if (titular == null)
            titular = new ArrayList<>();
        return titular;
    }

    public void setTitular(List<TitularDto> titular) {
        this.titular = titular;
    }

    public TraillerDto getTrailler() {
        return trailler;
    }

    public void setTrailler(TraillerDto trailler) {
        this.trailler = trailler;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(Sistema.FORMATO_DATA.getValue()).create().toJson(this);
    }

}
