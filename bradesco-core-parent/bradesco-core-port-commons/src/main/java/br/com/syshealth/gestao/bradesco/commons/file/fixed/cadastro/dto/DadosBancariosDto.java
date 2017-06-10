package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.DadosBancarios;

public class DadosBancariosDto {

    @DadosBancarios(prefix = "9", begin = 0, end = 3)
    private Integer numeroDaSubfatura;

    @DadosBancarios(prefix = "9", begin = 3, end = 10)
    private Integer numeroDoCertificado;

    @DadosBancarios(prefix = "9", begin = 10, end = 45)
    private String nomeDoSegurado;

    @DadosBancarios(prefix = "9", begin = 45, end = 56)
    private Integer numeroDaMatricula;

    @DadosBancarios(prefix = "9", begin = 56, end = 59)
    private Integer banco;

    @DadosBancarios(prefix = "9", begin = 59, end = 63)
    private Integer agencia;

    @DadosBancarios(prefix = "9", begin = 63, end = 64)
    private String primeiroDvDaAgencia;

    @DadosBancarios(prefix = "9", begin = 64, end = 65)
    private String segundoDvDaAgencia;

    @DadosBancarios(prefix = "9", begin = 65, end = 78)
    private Integer contaCorrente;

    @DadosBancarios(prefix = "9", begin = 78, end = 80)
    private String dvDaConta;

    @DadosBancarios(prefix = "9", begin = 80, end = 92)
    private String matriculaEspecial;

    @DadosBancarios(prefix = "9", begin = 92, end = 179)
    private String filler;

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

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getPrimeiroDvDaAgencia() {
        return primeiroDvDaAgencia;
    }

    public void setPrimeiroDvDaAgencia(String primeiroDvDaAgencia) {
        this.primeiroDvDaAgencia = primeiroDvDaAgencia;
    }

    public String getSegundoDvDaAgencia() {
        return segundoDvDaAgencia;
    }

    public void setSegundoDvDaAgencia(String segundoDvDaAgencia) {
        this.segundoDvDaAgencia = segundoDvDaAgencia;
    }

    public Integer getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Integer contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getDvDaConta() {
        return dvDaConta;
    }

    public void setDvDaConta(String dvDaConta) {
        this.dvDaConta = dvDaConta;
    }

    public String getMatriculaEspecial() {
        return matriculaEspecial;
    }

    public void setMatriculaEspecial(String matriculaEspecial) {
        this.matriculaEspecial = matriculaEspecial;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

}
