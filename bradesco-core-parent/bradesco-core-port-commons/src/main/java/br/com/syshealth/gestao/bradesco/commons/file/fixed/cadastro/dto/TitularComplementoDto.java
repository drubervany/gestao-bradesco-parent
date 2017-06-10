package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.TitularComplemento;

/**
 * The Class TitularDependenteDto.
 * 
 * @author Danilo.Rubervany
 */
public class TitularComplementoDto {

    @TitularComplemento(prefix = "5", begin = 0, end = 1)
    private String tipoDeRegistro;

    @TitularComplemento(prefix = "5", begin = 0, end = 3)
    private String numeroDaSubfatura;

    @TitularComplemento(prefix = "5", begin = 3, end = 10)
    private String numeroDoCertificado;

    @TitularComplemento(prefix = "5", begin = 10, end = 45)
    private String nomeDoSegurado;

    @TitularComplemento(prefix = "5", begin = 45, end = 56)
    private String matriculaFuncional;

    @TitularComplemento(prefix = "5", begin = 56, end = 71)
    private String numeroCartao;

    @TitularComplemento(prefix = "5", begin = 71, end = 106)
    private String nomeDaMae;

    @TitularComplemento(prefix = "5", begin = 106, end = 118)
    private String matriculaEspecial;

    @TitularComplemento(prefix = "5", begin = 118, end = 179)
    private String filler;

    public String getTipoDeRegistro() {
        return tipoDeRegistro;
    }

    public void setTipoDeRegistro(String tipoDeRegistro) {
        this.tipoDeRegistro = tipoDeRegistro;
    }

    public String getNumeroDaSubfatura() {
        return numeroDaSubfatura;
    }

    public void setNumeroDaSubfatura(String numeroDaSubfatura) {
        this.numeroDaSubfatura = numeroDaSubfatura;
    }

    public String getNumeroDoCertificado() {
        return numeroDoCertificado;
    }

    public void setNumeroDoCertificado(String numeroDoCertificado) {
        this.numeroDoCertificado = numeroDoCertificado;
    }

    public String getNomeDoSegurado() {
        return nomeDoSegurado;
    }

    public void setNomeDoSegurado(String nomeDoSegurado) {
        this.nomeDoSegurado = nomeDoSegurado;
    }

    public String getMatriculaFuncional() {
        return matriculaFuncional;
    }

    public void setMatriculaFuncional(String matriculaFuncional) {
        this.matriculaFuncional = matriculaFuncional;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
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
