package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import java.util.Date;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.annotation.DateFormatPattern;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TitularDependente;

/**
 * The Class TitularDependenteDto.
 * 
 * @author Danilo.Rubervany
 */
public class TitularDependenteDto {

	/** The numero da subfatura. */
	@TitularDependente(prefix = "3", begin = 0, end = 4)
	private Integer numeroDaSubfatura;

	/** The numero do certificado. */
	@TitularDependente(prefix = "3", begin = 4, end = 11)
	private Long numeroDoCertificado;

	/** The complemento do certificado. */
	@TitularDependente(prefix = "3", begin = 11, end = 13)
	private Integer complementoDoCertificado;

	/** The nome do segurado dependente. */
	@TitularDependente(prefix = "3", begin = 13, end = 48)
	private String nomeDoSeguradoDependente;

	/** The indic sub F anter atual. */
	@TitularDependente(prefix = "3", begin = 48, end = 52)
	private String indicSubFAnterAtual;

	/** The data de nascimento. */
	@TitularDependente(prefix = "3", begin = 52, end = 60)
	@DateFormatPattern("ddmmyyyy")
	private Date dataDeNascimento;

	/** The codigo sexo. */
	@TitularDependente(prefix = "3", begin = 60, end = 61)
	private Integer codigoSexo;

	/** The codigo est civil. */
	@TitularDependente(prefix = "3", begin = 61, end = 62)
	private Integer codigoEstCivil;

	/** The cod grau parent dep. */
	@TitularDependente(prefix = "3", begin = 62, end = 63)
	private Integer codGrauParentDep;

	/** The codigo do plano. */
	@TitularDependente(prefix = "3", begin = 63, end = 67)
	private String codigoDoPlano;

	/** The data inicio vigencia. */
	@TitularDependente(prefix = "3", begin = 67, end = 75)
	@DateFormatPattern("ddmmyyyy")
	private Date dataInicioVigencia;

	/** The tipo de lancamento. */
	@TitularDependente(prefix = "3", begin = 75, end = 77)
	private String tipoDeLancamento;

	/** The data de lancamento. */
	@TitularDependente(prefix = "3", begin = 77, end = 83)
	@DateFormatPattern("mmyyyy")
	private Date dataDeLancamento;

	/** The valor do lancamento. */
	@TitularDependente(prefix = "3", begin = 83, end = 98)
	private Double valorDoLancamento;

	/** The parte do segurado. */
	@TitularDependente(prefix = "3", begin = 98, end = 113)
	private Double parteDoSegurado;

	/** The codigo do lancamento. */
	@TitularDependente(prefix = "3", begin = 113, end = 115)
	private Integer codigoDoLancamento;

	/**
	 * Gets the numero da subfatura.
	 *
	 * @return the numero da subfatura
	 */
	public Integer getNumeroDaSubfatura() {
		return numeroDaSubfatura;
	}

	/**
	 * Sets the numero da subfatura.
	 *
	 * @param numeroDaSubfatura
	 *            the new numero da subfatura
	 */
	public void setNumeroDaSubfatura(Integer numeroDaSubfatura) {
		this.numeroDaSubfatura = numeroDaSubfatura;
	}

	/**
	 * Gets the numero do certificado.
	 *
	 * @return the numero do certificado
	 */
	public Long getNumeroDoCertificado() {
		return numeroDoCertificado;
	}

	/**
	 * Sets the numero do certificado.
	 *
	 * @param numeroDoCertificado
	 *            the new numero do certificado
	 */
	public void setNumeroDoCertificado(Long numeroDoCertificado) {
		this.numeroDoCertificado = numeroDoCertificado;
	}

	/**
	 * Gets the complemento do certificado.
	 *
	 * @return the complemento do certificado
	 */
	public Integer getComplementoDoCertificado() {
		return complementoDoCertificado;
	}

	/**
	 * Sets the complemento do certificado.
	 *
	 * @param complementoDoCertificado
	 *            the new complemento do certificado
	 */
	public void setComplementoDoCertificado(Integer complementoDoCertificado) {
		this.complementoDoCertificado = complementoDoCertificado;
	}

	/**
	 * Gets the nome do segurado dependente.
	 *
	 * @return the nome do segurado dependente
	 */
	public String getNomeDoSeguradoDependente() {
		return nomeDoSeguradoDependente;
	}

	/**
	 * Sets the nome do segurado dependente.
	 *
	 * @param nomeDoSeguradoDependente
	 *            the new nome do segurado dependente
	 */
	public void setNomeDoSeguradoDependente(String nomeDoSeguradoDependente) {
		this.nomeDoSeguradoDependente = nomeDoSeguradoDependente;
	}

	/**
	 * Gets the indic sub F anter atual.
	 *
	 * @return the indic sub F anter atual
	 */
	public String getIndicSubFAnterAtual() {
		return indicSubFAnterAtual;
	}

	/**
	 * Sets the indic sub F anter atual.
	 *
	 * @param indicSubFAnterAtual
	 *            the new indic sub F anter atual
	 */
	public void setIndicSubFAnterAtual(String indicSubFAnterAtual) {
		this.indicSubFAnterAtual = indicSubFAnterAtual;
	}

	/**
	 * Gets the data de nascimento.
	 *
	 * @return the data de nascimento
	 */
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	/**
	 * Sets the data de nascimento.
	 *
	 * @param dataDeNascimento
	 *            the new data de nascimento
	 */
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	/**
	 * Gets the codigo sexo.
	 *
	 * @return the codigo sexo
	 */
	public Integer getCodigoSexo() {
		return codigoSexo;
	}

	/**
	 * Sets the codigo sexo.
	 *
	 * @param codigoSexo
	 *            the new codigo sexo
	 */
	public void setCodigoSexo(Integer codigoSexo) {
		this.codigoSexo = codigoSexo;
	}

	/**
	 * Gets the codigo est civil.
	 *
	 * @return the codigo est civil
	 */
	public Integer getCodigoEstCivil() {
		return codigoEstCivil;
	}

	/**
	 * Sets the codigo est civil.
	 *
	 * @param codigoEstCivil
	 *            the new codigo est civil
	 */
	public void setCodigoEstCivil(Integer codigoEstCivil) {
		this.codigoEstCivil = codigoEstCivil;
	}

	/**
	 * Gets the cod grau parent dep.
	 *
	 * @return the cod grau parent dep
	 */
	public Integer getCodGrauParentDep() {
		return codGrauParentDep;
	}

	/**
	 * Sets the cod grau parent dep.
	 *
	 * @param codGrauParentDep
	 *            the new cod grau parent dep
	 */
	public void setCodGrauParentDep(Integer codGrauParentDep) {
		this.codGrauParentDep = codGrauParentDep;
	}

	/**
	 * Gets the codigo do plano.
	 *
	 * @return the codigo do plano
	 */
	public String getCodigoDoPlano() {
		return codigoDoPlano;
	}

	/**
	 * Sets the codigo do plano.
	 *
	 * @param codigoDoPlano
	 *            the new codigo do plano
	 */
	public void setCodigoDoPlano(String codigoDoPlano) {
		this.codigoDoPlano = codigoDoPlano;
	}

	/**
	 * Gets the data inicio vigencia.
	 *
	 * @return the data inicio vigencia
	 */
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	/**
	 * Sets the data inicio vigencia.
	 *
	 * @param dataInicioVigencia
	 *            the new data inicio vigencia
	 */
	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	/**
	 * Gets the tipo de lancamento.
	 *
	 * @return the tipo de lancamento
	 */
	public String getTipoDeLancamento() {
		return tipoDeLancamento;
	}

	/**
	 * Sets the tipo de lancamento.
	 *
	 * @param tipoDeLancamento
	 *            the new tipo de lancamento
	 */
	public void setTipoDeLancamento(String tipoDeLancamento) {
		this.tipoDeLancamento = tipoDeLancamento;
	}

	/**
	 * Gets the data de lancamento.
	 *
	 * @return the data de lancamento
	 */
	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	/**
	 * Sets the data de lancamento.
	 *
	 * @param dataDeLancamento
	 *            the new data de lancamento
	 */
	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	/**
	 * Gets the valor do lancamento.
	 *
	 * @return the valor do lancamento
	 */
	public Double getValorDoLancamento() {
		return valorDoLancamento;
	}

	/**
	 * Sets the valor do lancamento.
	 *
	 * @param valorDoLancamento
	 *            the new valor do lancamento
	 */
	public void setValorDoLancamento(Double valorDoLancamento) {
		this.valorDoLancamento = valorDoLancamento;
	}

	/**
	 * Gets the parte do segurado.
	 *
	 * @return the parte do segurado
	 */
	public Double getParteDoSegurado() {
		return parteDoSegurado;
	}

	/**
	 * Sets the parte do segurado.
	 *
	 * @param parteDoSegurado
	 *            the new parte do segurado
	 */
	public void setParteDoSegurado(Double parteDoSegurado) {
		this.parteDoSegurado = parteDoSegurado;
	}

	/**
	 * Gets the codigo do lancamento.
	 *
	 * @return the codigo do lancamento
	 */
	public Integer getCodigoDoLancamento() {
		return codigoDoLancamento;
	}

	/**
	 * Sets the codigo do lancamento.
	 *
	 * @param codigoDoLancamento
	 *            the new codigo do lancamento
	 */
	public void setCodigoDoLancamento(Integer codigoDoLancamento) {
		this.codigoDoLancamento = codigoDoLancamento;
	}

	@Override
	public String toString() {
		return "TitularDependenteDto [numeroDaSubfatura=" + numeroDaSubfatura + ", numeroDoCertificado="
				+ numeroDoCertificado + ", complementoDoCertificado=" + complementoDoCertificado
				+ ", nomeDoSeguradoDependente=" + nomeDoSeguradoDependente + ", indicSubFAnterAtual="
				+ indicSubFAnterAtual + ", dataDeNascimento=" + dataDeNascimento + ", codigoSexo=" + codigoSexo
				+ ", codigoEstCivil=" + codigoEstCivil + ", codGrauParentDep=" + codGrauParentDep + ", codigoDoPlano="
				+ codigoDoPlano + ", dataInicioVigencia=" + dataInicioVigencia + ", tipoDeLancamento="
				+ tipoDeLancamento + ", dataDeLancamento=" + dataDeLancamento + ", valorDoLancamento="
				+ valorDoLancamento + ", parteDoSegurado=" + parteDoSegurado + ", codigoDoLancamento="
				+ codigoDoLancamento + "]";
	}

}
