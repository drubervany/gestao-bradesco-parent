package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import java.util.Date;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Dependente;

public class DependenteDto {

    @Dependente(prefix = "3", begin = 0, end = 3)
    private String numeroDaSubfatura;

    @Dependente(prefix = "3", begin = 3, end = 10)
    private Integer numeroDoCertificado;

    @Dependente(prefix = "3", begin = 10, end = 12)
    private Integer codigoDoDependente;

    @Dependente(prefix = "3", begin = 12, end = 47)
    private String nomeDoDependente;

    @Dependente(prefix = "3", begin = 47, end = 53)
    @DateFormatPattern("yymmdd")
    private Date dataDeNascimento_;

    @Dependente(prefix = "3", begin = 53, end = 54)
    private Integer sexoDoDependente;

    @Dependente(prefix = "3", begin = 54, end = 55)
    private Integer estadoCivilDoDependente;

    @Dependente(prefix = "3", begin = 55, end = 56)
    private Integer grauDeParentesco;

    @Dependente(prefix = "3", begin = 56, end = 62)
    @DateFormatPattern("yymmdd")
    private Date dataDeInicioDeVigencia_;

    @Dependente(prefix = "3", begin = 62, end = 74)
    private String matriculaEspecialTitular;

    @Dependente(prefix = "3", begin = 74, end = 82)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeNascimento;

    @Dependente(prefix = "3", begin = 82, end = 90)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeInicioDeVigencia;

    @Dependente(prefix = "3", begin = 90, end = 102)
    private String matriculaEspecialDepend;

    @Dependente(prefix = "3", begin = 102, end = 110)
    @DateFormatPattern("yyyymmdd")
    private Date dataDaReativacao;

    @Dependente(prefix = "3", begin = 110, end = 118)
    @DateFormatPattern("yymmdd")
    private Date dataDoCancelamento;

    @Dependente(prefix = "3", begin = 118, end = 129)
    private String cpfDependente;

    @Dependente(prefix = "3", begin = 129, end = 144)
    private String numeroDoCartao;

    @Dependente(prefix = "3", begin = 144, end = 179)
    private String nomeDaMae;

    public String getNumeroDaSubfatura() {
        return numeroDaSubfatura;
    }

    public void setNumeroDaSubfatura(String numeroDaSubfatura) {
        this.numeroDaSubfatura = numeroDaSubfatura;
    }

    public Integer getNumeroDoCertificado() {
        return numeroDoCertificado;
    }

    public void setNumeroDoCertificado(Integer numeroDoCertificado) {
        this.numeroDoCertificado = numeroDoCertificado;
    }

    public Integer getCodigoDoDependente() {
        return codigoDoDependente;
    }

    public void setCodigoDoDependente(Integer codigoDoDependente) {
        this.codigoDoDependente = codigoDoDependente;
    }

    public String getNomeDoDependente() {
        return nomeDoDependente;
    }

    public void setNomeDoDependente(String nomeDoDependente) {
        this.nomeDoDependente = nomeDoDependente;
    }

    public Date getDataDeNascimento_() {
        return dataDeNascimento_;
    }

    public void setDataDeNascimento_(Date dataDeNascimento_) {
        this.dataDeNascimento_ = dataDeNascimento_;
    }

    public Integer getSexoDoDependente() {
        return sexoDoDependente;
    }

    public void setSexoDoDependente(Integer sexoDoDependente) {
        this.sexoDoDependente = sexoDoDependente;
    }

    public Integer getEstadoCivilDoDependente() {
        return estadoCivilDoDependente;
    }

    public void setEstadoCivilDoDependente(Integer estadoCivilDoDependente) {
        this.estadoCivilDoDependente = estadoCivilDoDependente;
    }

    public Integer getGrauDeParentesco() {
        return grauDeParentesco;
    }

    public void setGrauDeParentesco(Integer grauDeParentesco) {
        this.grauDeParentesco = grauDeParentesco;
    }

    public Date getDataDeInicioDeVigencia_() {
        return dataDeInicioDeVigencia_;
    }

    public void setDataDeInicioDeVigencia_(Date dataDeInicioDeVigencia_) {
        this.dataDeInicioDeVigencia_ = dataDeInicioDeVigencia_;
    }

    public String getMatriculaEspecialTitular() {
        return matriculaEspecialTitular;
    }

    public void setMatriculaEspecialTitular(String matriculaEspecialTitular) {
        this.matriculaEspecialTitular = matriculaEspecialTitular;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeInicioDeVigencia() {
        return dataDeInicioDeVigencia;
    }

    public void setDataDeInicioDeVigencia(Date dataDeInicioDeVigencia) {
        this.dataDeInicioDeVigencia = dataDeInicioDeVigencia;
    }

    public String getMatriculaEspecialDepend() {
        return matriculaEspecialDepend;
    }

    public void setMatriculaEspecialDepend(String matriculaEspecialDepend) {
        this.matriculaEspecialDepend = matriculaEspecialDepend;
    }

    public Date getDataDaReativacao() {
        return dataDaReativacao;
    }

    public void setDataDaReativacao(Date dataDaReativacao) {
        this.dataDaReativacao = dataDaReativacao;
    }

    public Date getDataDoCancelamento() {
        return dataDoCancelamento;
    }

    public void setDataDoCancelamento(Date dataDoCancelamento) {
        this.dataDoCancelamento = dataDoCancelamento;
    }

    public String getCpfDependente() {
        return cpfDependente;
    }

    public void setCpfDependente(String cpfDependente) {
        this.cpfDependente = cpfDependente;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

}
