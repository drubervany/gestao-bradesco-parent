package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.DadosBancarios;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Dependente;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Titular;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.TitularComplemento;

/**
 * The Class HeaderSubFaturaDto.
 * 
 * @author Danilo.Rubervany
 */
public class TitularDto {

    @Titular(prefix = "2", begin = 0, end = 3)
    private Integer numeroDaSubfatura;

    @Titular(prefix = "2", begin = 3, end = 10)
    private Integer numeroDoCertificado;

    @Titular(prefix = "2", begin = 10, end = 45)
    private String nomeDoSegurado;

    @Titular(prefix = "2", begin = 45, end = 56)
    private Integer numeroDaMatricula;

    @Titular(prefix = "2", begin = 56, end = 57)
    private Integer sexoDoSegurado;

    @Titular(prefix = "2", begin = 57, end = 63)
    @DateFormatPattern("yymmdd")
    private Date dataDeNascimento_;

    @Titular(prefix = "2", begin = 63, end = 64)
    private Integer estadoCivil;

    @Titular(prefix = "2", begin = 64, end = 75)
    private String cpf;

    @Titular(prefix = "2", begin = 75, end = 95)
    private String cargoOcupacao;

    @Titular(prefix = "2", begin = 95, end = 101)
    @DateFormatPattern("yymmdd")
    private Date dataDeAdmissao_;

    @Titular(prefix = "2", begin = 101, end = 107)
    @DateFormatPattern("yymmdd")
    private Date dataDeInicioDeVigencia;

    @Titular(prefix = "2", begin = 107, end = 111)
    private String plano;

    @Titular(prefix = "2", begin = 111, end = 123)
    private String matriculaEspecial;

    @Titular(prefix = "2", begin = 123, end = 131)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeNascimento;

    @Titular(prefix = "2", begin = 131, end = 139)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeAdmissao;

    @Titular(prefix = "2", begin = 139, end = 147)
    @DateFormatPattern("yymmdd")
    private Date dataDeInicioVigencia;

    @Titular(prefix = "2", begin = 147, end = 155)
    private String filler_;

    @Titular(prefix = "2", begin = 155, end = 163)
    @DateFormatPattern("yyyymmdd")
    private Date dataDaReativacao;

    @Titular(prefix = "2", begin = 163, end = 167)
    private Integer codigoRegiao;

    @Titular(prefix = "2", begin = 167, end = 179)
    private String filler;

    @TitularComplemento(prefix = "5")
    private TitularComplementoDto titularComplemento;

    @DadosBancarios(prefix = "9")
    private DadosBancariosDto dadosBancarios;

    @Dependente(prefix = "9")
    private List<DependenteDto> dependente;

    public Integer getNumeroDaSubfatura() {
        return numeroDaSubfatura;
    }

    public void setNumeroDaSubfatura(Integer numeroDaSubfatura) {
        this.numeroDaSubfatura = numeroDaSubfatura;
    }

    public Integer getNumeroDoCertificado() {
        return numeroDoCertificado;
    }

    public void setNumeroDoCertificado(Integer numeroDoCertificado) {
        this.numeroDoCertificado = numeroDoCertificado;
    }

    public String getNomeDoSegurado() {
        return nomeDoSegurado;
    }

    public void setNomeDoSegurado(String nomeDoSegurado) {
        this.nomeDoSegurado = nomeDoSegurado;
    }

    public Integer getNumeroDaMatricula() {
        return numeroDaMatricula;
    }

    public void setNumeroDaMatricula(Integer numeroDaMatricula) {
        this.numeroDaMatricula = numeroDaMatricula;
    }

    public Integer getSexoDoSegurado() {
        return sexoDoSegurado;
    }

    public void setSexoDoSegurado(Integer sexoDoSegurado) {
        this.sexoDoSegurado = sexoDoSegurado;
    }

    public Date getDataDeNascimento_() {
        return dataDeNascimento_;
    }

    public void setDataDeNascimento_(Date dataDeNascimento_) {
        this.dataDeNascimento_ = dataDeNascimento_;
    }

    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargoOcupacao() {
        return cargoOcupacao;
    }

    public void setCargoOcupacao(String cargoOcupacao) {
        this.cargoOcupacao = cargoOcupacao;
    }

    public Date getDataDeAdmissao_() {
        return dataDeAdmissao_;
    }

    public void setDataDeAdmissao_(Date dataDeAdmissao_) {
        this.dataDeAdmissao_ = dataDeAdmissao_;
    }

    public Date getDataDeInicioDeVigencia() {
        return dataDeInicioDeVigencia;
    }

    public void setDataDeInicioDeVigencia(Date dataDeInicioDeVigencia) {
        this.dataDeInicioDeVigencia = dataDeInicioDeVigencia;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getMatriculaEspecial() {
        return matriculaEspecial;
    }

    public void setMatriculaEspecial(String matriculaEspecial) {
        this.matriculaEspecial = matriculaEspecial;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public Date getDataDeInicioVigencia() {
        return dataDeInicioVigencia;
    }

    public void setDataDeInicioVigencia(Date dataDeInicioVigencia) {
        this.dataDeInicioVigencia = dataDeInicioVigencia;
    }

    public String getFiller_() {
        return filler_;
    }

    public void setFiller_(String filler_) {
        this.filler_ = filler_;
    }

    public Date getDataDaReativacao() {
        return dataDaReativacao;
    }

    public void setDataDaReativacao(Date dataDaReativacao) {
        this.dataDaReativacao = dataDaReativacao;
    }

    public Integer getCodigoRegiao() {
        return codigoRegiao;
    }

    public void setCodigoRegiao(Integer codigoRegiao) {
        this.codigoRegiao = codigoRegiao;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public TitularComplementoDto getTitularComplemento() {
        return titularComplemento;
    }

    public void setTitularComplemento(TitularComplementoDto titularComplemento) {
        this.titularComplemento = titularComplemento;
    }

    public DadosBancariosDto getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(DadosBancariosDto dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public List<DependenteDto> getDependente() {
        if (dependente == null)
            dependente = new ArrayList<>();
        return dependente;
    }

    public void setDependente(List<DependenteDto> dependente) {
        this.dependente = dependente;
    }

}
