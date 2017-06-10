package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Movimento;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Trailler;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
public class SinistroDto {

    @Header(prefix = "H", begin = 0, end = 15)
    private String tipoDeArquivo;

    @Header(prefix = "H", begin = 15, end = 21)
    private Integer numeroDaApolice;

    @Header(prefix = "H", begin = 21, end = 28)
    private Integer dataDeCompetencia_;

    @Header(prefix = "H", begin = 28, end = 36)
    private String dataDeProcessamento_;

    @Header(prefix = "H", begin = 36, end = 45)
    private String filler_;

    @Header(prefix = "H", begin = 45, end = 55)
    private Integer dataDeCompetencia;

    @Header(prefix = "H", begin = 55, end = 66)
    private String dataDeProcessamento;

    @Header(prefix = "H", begin = 66, end = 78)
    private String filler;

    @Movimento(prefix = "M")
    private List<MovimentoDto> movimento;

    @Trailler(prefix = "T")
    private TraillerDto trailler;

    public String getTipoDeArquivo() {
        return tipoDeArquivo;
    }

    public Integer getNumeroDaApolice() {
        return numeroDaApolice;
    }

    public Integer getDataDeCompetencia_() {
        return dataDeCompetencia_;
    }

    public String getDataDeProcessamento_() {
        return dataDeProcessamento_;
    }

    public String getFiller_() {
        return filler_;
    }

    public Integer getDataDeCompetencia() {
        return dataDeCompetencia;
    }

    public String getDataDeProcessamento() {
        return dataDeProcessamento;
    }

    public String getFiller() {
        return filler;
    }

    public List<MovimentoDto> getMovimento() {
        if (movimento == null)
            movimento = new ArrayList<>();
        return movimento;
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
