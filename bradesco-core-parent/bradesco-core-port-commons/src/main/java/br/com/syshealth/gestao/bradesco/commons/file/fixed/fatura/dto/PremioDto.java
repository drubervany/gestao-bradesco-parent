package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.GsonBuilder;

import br.com.syshealth.commons.utils.Sistema;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.Header;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.HeaderSubFatura;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.Trailler;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerObservacao;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerSubFatura;

/**
 * The Class FaturaDto.
 * 
 * @author Danilo.Rubervany
 */
public class PremioDto {

    /** The tipo de arquivo. */
    @Header(prefix = "1", begin = 0, end = 30)
    private String tipoDeArquivo;

    /** The nome do estipulante. */
    @Header(prefix = "1", begin = 30, end = 65)
    private String nomeDoEstipulante;

    /** The codigo da companhia. */
    @Header(prefix = "1", begin = 65, end = 68)
    private Integer codigoDaCompanhia;

    /** The codigo da sucursal. */
    @Header(prefix = "1", begin = 68, end = 71)
    private Integer codigoDaSucursal;

    /** The codigo do contrato. */
    @Header(prefix = "1", begin = 71, end = 77)
    private Long codigoDoContrato;

    /** The codigo do ramo. */
    @Header(prefix = "1", begin = 77, end = 80)
    private Integer codigoDoRamo;

    /** The descricao do ramo. */
    @Header(prefix = "1", begin = 80, end = 115)
    private String descricaoDoRamo;

    /** The competencia da fatura. */
    @Header(prefix = "1", begin = 115, end = 121)
    // @DateFormatPattern("yyyymm")
    private Integer competenciaDaFatura;

    /** The data do processamento. */
    @Header(prefix = "1", begin = 121, end = 129)
    @DateFormatPattern("yyyymmdd")
    private Date dataDoProcessamento;

    /** The filler. */
    @Header(prefix = "1", begin = 129, end = 179)
    private String filler;

    /** The header sub fatura. */
    @HeaderSubFatura(prefix = "2")
    private List<HeaderSubFaturaDto> headerSubFatura;

    /** The trailler fatura. */
    @TraillerSubFatura(prefix = "4")
    private List<TraillerSubFaturaDto> traillerFatura;

    /** The trailler observacao. */
    @TraillerObservacao(prefix = "4")
    private List<TraillerObservacaoDto> traillerObservacao;

    /** The trailler. */
    @Trailler(prefix = "5")
    private TraillerDto trailler;

    /**
     * Gets the tipo de arquivo.
     *
     * @return the tipo de arquivo
     */
    public String getTipoDeArquivo() {
        return tipoDeArquivo;
    }

    /**
     * Sets the tipo de arquivo.
     *
     * @param tipoDeArquivo
     *            the new tipo de arquivo
     */
    public void setTipoDeArquivo(String tipoDeArquivo) {
        this.tipoDeArquivo = tipoDeArquivo;
    }

    /**
     * Gets the nome do estipulante.
     *
     * @return the nome do estipulante
     */
    public String getNomeDoEstipulante() {
        return nomeDoEstipulante;
    }

    /**
     * Sets the nome do estipulante.
     *
     * @param nomeDoEstipulante
     *            the new nome do estipulante
     */
    public void setNomeDoEstipulante(String nomeDoEstipulante) {
        this.nomeDoEstipulante = nomeDoEstipulante;
    }

    /**
     * Gets the codigo da companhia.
     *
     * @return the codigo da companhia
     */
    public Integer getCodigoDaCompanhia() {
        return codigoDaCompanhia;
    }

    /**
     * Sets the codigo da companhia.
     *
     * @param codigoDaCompanhia
     *            the new codigo da companhia
     */
    public void setCodigoDaCompanhia(Integer codigoDaCompanhia) {
        this.codigoDaCompanhia = codigoDaCompanhia;
    }

    /**
     * Gets the codigo da sucursal.
     *
     * @return the codigo da sucursal
     */
    public Integer getCodigoDaSucursal() {
        return codigoDaSucursal;
    }

    /**
     * Sets the codigo da sucursal.
     *
     * @param codigoDaSucursal
     *            the new codigo da sucursal
     */
    public void setCodigoDaSucursal(Integer codigoDaSucursal) {
        this.codigoDaSucursal = codigoDaSucursal;
    }

    /**
     * Gets the codigo do contrato.
     *
     * @return the codigo do contrato
     */
    public Long getCodigoDoContrato() {
        return codigoDoContrato;
    }

    /**
     * Sets the codigo do contrato.
     *
     * @param codigoDoContrato
     *            the new codigo do contrato
     */
    public void setCodigoDoContrato(Long codigoDoContrato) {
        this.codigoDoContrato = codigoDoContrato;
    }

    /**
     * Gets the codigo do ramo.
     *
     * @return the codigo do ramo
     */
    public Integer getCodigoDoRamo() {
        return codigoDoRamo;
    }

    /**
     * Sets the codigo do ramo.
     *
     * @param codigoDoRamo
     *            the new codigo do ramo
     */
    public void setCodigoDoRamo(Integer codigoDoRamo) {
        this.codigoDoRamo = codigoDoRamo;
    }

    /**
     * Gets the descricao do ramo.
     *
     * @return the descricao do ramo
     */
    public String getDescricaoDoRamo() {
        return descricaoDoRamo;
    }

    /**
     * Sets the descricao do ramo.
     *
     * @param descricaoDoRamo
     *            the new descricao do ramo
     */
    public void setDescricaoDoRamo(String descricaoDoRamo) {
        this.descricaoDoRamo = descricaoDoRamo;
    }

    /**
     * Gets the competencia da fatura.
     *
     * @return the competencia da fatura
     */
    public Integer getCompetenciaDaFatura() {
        return competenciaDaFatura;
    }

    /**
     * Sets the competencia da fatura.
     *
     * @param competenciaDaFatura
     *            the new competencia da fatura
     */
    public void setCompetenciaDaFatura(Integer competenciaDaFatura) {
        this.competenciaDaFatura = competenciaDaFatura;
    }

    /**
     * Gets the data do processamento.
     *
     * @return the data do processamento
     */
    public Date getDataDoProcessamento() {
        return dataDoProcessamento;
    }

    /**
     * Sets the data do processamento.
     *
     * @param dataDoProcessamento
     *            the new data do processamento
     */
    public void setDataDoProcessamento(Date dataDoProcessamento) {
        this.dataDoProcessamento = dataDoProcessamento;
    }

    /**
     * Gets the filler.
     *
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }

    /**
     * Sets the filler.
     *
     * @param filler
     *            the new filler
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }

    /**
     * Gets the header sub fatura.
     *
     * @return the header sub fatura
     */
    public List<HeaderSubFaturaDto> getHeaderSubFatura() {
        if (headerSubFatura == null) {
            headerSubFatura = new ArrayList<>();
        }
        return headerSubFatura;
    }

    /**
     * Sets the header sub fatura.
     *
     * @param headerSubFatura
     *            the new header sub fatura
     */
    public void setHeaderSubFatura(List<HeaderSubFaturaDto> headerSubFatura) {
        this.headerSubFatura = headerSubFatura;
    }

    /**
     * Gets the trailler.
     *
     * @return the trailler
     */
    public TraillerDto getTrailler() {
        return trailler;
    }

    /**
     * Sets the trailler.
     *
     * @param trailler
     *            the new trailler
     */
    public void setTrailler(TraillerDto trailler) {
        this.trailler = trailler;
    }

    /**
     * Gets the trailler fatura.
     *
     * @return the trailler fatura
     */
    public List<TraillerSubFaturaDto> getTraillerFatura() {
        if (traillerFatura == null)
            traillerFatura = new ArrayList<>();
        return traillerFatura;
    }

    /**
     * Sets the trailler fatura.
     *
     * @param traillerFatura
     *            the new trailler fatura
     */
    public void setTraillerFatura(List<TraillerSubFaturaDto> traillerFatura) {
        this.traillerFatura = traillerFatura;
    }

    /**
     * Gets the trailler observacao.
     *
     * @return the trailler observacao
     */
    public List<TraillerObservacaoDto> getTraillerObservacao() {
        if (traillerObservacao == null)
            traillerObservacao = new ArrayList<>();
        return traillerObservacao;
    }

    /**
     * Sets the trailler observacao.
     *
     * @param traillerObservacao
     *            the new trailler observacao
     */
    public void setTraillerObservacao(List<TraillerObservacaoDto> traillerObservacao) {
        this.traillerObservacao = traillerObservacao;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(Sistema.FORMATO_DATA.getValue()).create().toJson(this);
    }

    @Override
    public String toString() {
        return "FaturaDto [tipoDeArquivo=" + tipoDeArquivo + ", nomeDoEstipulante=" + nomeDoEstipulante
                + ", codigoDaCompanhia=" + codigoDaCompanhia + ", codigoDaSucursal=" + codigoDaSucursal
                + ", codigoDoContrato=" + codigoDoContrato + ", codigoDoRamo=" + codigoDoRamo + ", descricaoDoRamo="
                + descricaoDoRamo + ", competenciaDaFatura=" + competenciaDaFatura + ", dataDoProcessamento="
                + dataDoProcessamento + ", filler=" + filler + ", headerSubFatura=" + headerSubFatura
                + ", traillerFatura=" + traillerFatura + ", traillerObservacao=" + traillerObservacao + ", trailler="
                + trailler + "]";
    }

}
