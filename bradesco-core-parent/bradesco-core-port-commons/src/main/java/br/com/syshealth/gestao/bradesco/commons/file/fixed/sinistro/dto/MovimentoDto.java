package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto;

import java.util.Date;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Movimento;

/**
 * The Class TitularDependenteDto.
 * 
 * @author Danilo.Rubervany
 */
public class MovimentoDto {

    @Movimento(prefix = "M", begin = 0, end = 3)
    private Integer numeroDaSubfatura;

    @Movimento(prefix = "M", begin = 3, end = 38)
    private String nomeDaSubfatura;

    @Movimento(prefix = "M", begin = 38, end = 40)
    private String tipoDaSubfatura;

    @Movimento(prefix = "M", begin = 40, end = 47)
    private Long numeroDoCertificado;

    @Movimento(prefix = "M", begin = 47, end = 57)
    private String matricula;

    @Movimento(prefix = "M", begin = 57, end = 92)
    private String nomeDoSegurado;

    @Movimento(prefix = "M", begin = 92, end = 94)
    private Integer codigoDoPaciente;

    @Movimento(prefix = "M", begin = 94, end = 129)
    private String nomeDoPaciente;

    @Movimento(prefix = "M", begin = 129, end = 164)
    private String nomeDoBeneficiario;

    @Movimento(prefix = "M", begin = 164, end = 165)
    private String tipoDeEvento;

    @Movimento(prefix = "M", begin = 165, end = 175)
    private String numeroDoDocumento;

    @Movimento(prefix = "M", begin = 175, end = 183)
    private Long codigoDoProcedimento;

    @Movimento(prefix = "M", begin = 183, end = 185)
    private Integer quantidadeProcedimentos;

    @Movimento(prefix = "M", begin = 185, end = 191)
    @DateFormatPattern("yymmdd")
    private Date dataDoPagamento_;

    @Movimento(prefix = "M", begin = 191, end = 205)
    private Double valorPago;

    @Movimento(prefix = "M", begin = 205, end = 211)
    @DateFormatPattern("yymmdd")
    private Date dataDoEvento_;

    @Movimento(prefix = "M", begin = 211, end = 217)
    private String numeroDoContrato;

    @Movimento(prefix = "M", begin = 217, end = 227)
    private Long codigoDoReferenciado;

    @Movimento(prefix = "M", begin = 227, end = 229)
    private String sadtParaPacienteInternado;

    @Movimento(prefix = "M", begin = 229, end = 341)
    private String filler_;

    @Movimento(prefix = "M", begin = 341, end = 347)
    @DateFormatPattern("yymmdd")
    private Date dataDeNascimento_;

    @Movimento(prefix = "M", begin = 347, end = 348)
    private Integer sexo;

    @Movimento(prefix = "M", begin = 348, end = 349)
    private Integer grauDeParentesco;

    @Movimento(prefix = "M", begin = 349, end = 351)
    private String especialidade;

    @Movimento(prefix = "M", begin = 351, end = 365)
    private Double valorDoSinistro;

    @Movimento(prefix = "M", begin = 365, end = 377)
    private String matriculaEspecial;

    @Movimento(prefix = "M", begin = 377, end = 379)
    private String filler;

    @Movimento(prefix = "M", begin = 379, end = 381)
    private String codigoAutorizacao;

    @Movimento(prefix = "M", begin = 381, end = 395)
    private String cpfCgcDoReferenciado;

    @Movimento(prefix = "M", begin = 395, end = 396)
    private String tipoDoReferenciado;

    @Movimento(prefix = "M", begin = 396, end = 410)
    private Double valorDeInssOuIss;

    @Movimento(prefix = "M", begin = 410, end = 424)
    private Double valorDeInssOuIssFAJTR;

    @Movimento(prefix = "M", begin = 424, end = 444)
    private String cargoDoSegurado;

    @Movimento(prefix = "M", begin = 444, end = 452)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeAdmissao;

    @Movimento(prefix = "M", begin = 452, end = 456)
    private String planoDoSegurado;

    @Movimento(prefix = "M", begin = 456, end = 464)
    private String cdb;

    @Movimento(prefix = "M", begin = 464, end = 472)
    @DateFormatPattern("yyyymmdd")
    private Date dataDoPagamento;

    @Movimento(prefix = "M", begin = 472, end = 480)
    @DateFormatPattern("yyyymmdd")
    private Date dataDoEvento;

    @Movimento(prefix = "M", begin = 480, end = 488)
    @DateFormatPattern("yyyymmdd")
    private Date dataDeNascimento;

    @Movimento(prefix = "M", begin = 488, end = 489)
    private String trocaDeAcomodacao;

    @Movimento(prefix = "M", begin = 489, end = 499)
    private Double valorDoRecibo;

    public Integer getNumeroDaSubfatura() {
        return numeroDaSubfatura;
    }

    public String getNomeDaSubfatura() {
        return nomeDaSubfatura;
    }

    public String getTipoDaSubfatura() {
        return tipoDaSubfatura;
    }

    public Long getNumeroDoCertificado() {
        return numeroDoCertificado;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNomeDoSegurado() {
        return nomeDoSegurado;
    }

    public Integer getCodigoDoPaciente() {
        return codigoDoPaciente;
    }

    public String getNomeDoPaciente() {
        return nomeDoPaciente;
    }

    public String getNomeDoBeneficiario() {
        return nomeDoBeneficiario;
    }

    public String getTipoDeEvento() {
        return tipoDeEvento;
    }

    public String getNumeroDoDocumento() {
        return numeroDoDocumento;
    }

    public Long getCodigoDoProcedimento() {
        return codigoDoProcedimento;
    }

    public Integer getQuantidadeProcedimentos() {
        return quantidadeProcedimentos;
    }

    public Date getDataDoPagamento_() {
        return dataDoPagamento_;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public Date getDataDoEvento_() {
        return dataDoEvento_;
    }

    public String getNumeroDoContrato() {
        return numeroDoContrato;
    }

    public Long getCodigoDoReferenciado() {
        return codigoDoReferenciado;
    }

    public String getSadtParaPacienteInternado() {
        return sadtParaPacienteInternado;
    }

    public String getFiller_() {
        return filler_;
    }

    public Date getDataDeNascimento_() {
        return dataDeNascimento_;
    }

    public Integer getSexo() {
        return sexo;
    }

    public Integer getGrauDeParentesco() {
        return grauDeParentesco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Double getValorDoSinistro() {
        return valorDoSinistro;
    }

    public String getMatriculaEspecial() {
        return matriculaEspecial;
    }

    public String getFiller() {
        return filler;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public String getCpfCgcDoReferenciado() {
        return cpfCgcDoReferenciado;
    }

    public String getTipoDoReferenciado() {
        return tipoDoReferenciado;
    }

    public Double getValorDeInssOuIss() {
        return valorDeInssOuIss;
    }

    public Double getValorDeInssOuIssFAJTR() {
        return valorDeInssOuIssFAJTR;
    }

    public String getCargoDoSegurado() {
        return cargoDoSegurado;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public String getPlanoDoSegurado() {
        return planoDoSegurado;
    }

    public String getCdb() {
        return cdb;
    }

    public Date getDataDoPagamento() {
        return dataDoPagamento;
    }

    public Date getDataDoEvento() {
        return dataDoEvento;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getTrocaDeAcomodacao() {
        return trocaDeAcomodacao;
    }

    public Double getValorDoRecibo() {
        return valorDoRecibo;
    }

}
