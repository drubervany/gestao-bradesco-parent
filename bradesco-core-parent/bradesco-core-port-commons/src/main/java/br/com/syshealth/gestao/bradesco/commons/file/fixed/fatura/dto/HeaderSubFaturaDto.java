/*
 * 
 */
package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.HeaderSubFatura;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TitularDependente;
import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerSubFatura;

// TODO: Auto-generated Javadoc
/**
 * The Class HeaderSubFaturaDto.
 * 
 * @author Danilo.Rubervany
 * 
 */
public class HeaderSubFaturaDto {

	/** The sequencia da fatura. */
	@HeaderSubFatura(prefix = "2", begin = 0, end = 2)
	private Integer sequenciaDaFatura;

	/** The numero da subfatura. */
	@HeaderSubFatura(prefix = "2", begin = 2, end = 6)
	private Integer numeroDaSubfatura;

	/** The nome da subfatura. */
	@HeaderSubFatura(prefix = "2", begin = 6, end = 41)
	private String nomeDaSubfatura;

	/** The filler. */
	@HeaderSubFatura(prefix = "2", begin = 41, end = 179)
	private String filler;

	/** The titular dependente. */
	@TitularDependente(prefix = "3")
	private List<TitularDependenteDto> titularDependente;

	/** The trailler sub fatura. */
	@TraillerSubFatura(prefix = "4")
	private List<TraillerSubFaturaDto> traillerSubFatura;

	/** The trailler observacao. */
	@TraillerSubFatura(prefix = "4")
	private List<TraillerObservacaoDto> traillerObservacao;

	/**
	 * Gets the sequencia da fatura.
	 *
	 * @return the sequencia da fatura
	 */
	public Integer getSequenciaDaFatura() {
		return sequenciaDaFatura;
	}

	/**
	 * Sets the sequencia da fatura.
	 *
	 * @param sequenciaDaFatura
	 *            the new sequencia da fatura
	 */
	public void setSequenciaDaFatura(Integer sequenciaDaFatura) {
		this.sequenciaDaFatura = sequenciaDaFatura;
	}

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
	 * Gets the nome da subfatura.
	 *
	 * @return the nome da subfatura
	 */
	public String getNomeDaSubfatura() {
		return nomeDaSubfatura;
	}

	/**
	 * Sets the nome da subfatura.
	 *
	 * @param nomeDaSubfatura
	 *            the new nome da subfatura
	 */
	public void setNomeDaSubfatura(String nomeDaSubfatura) {
		this.nomeDaSubfatura = nomeDaSubfatura;
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
	 * Gets the titular dependente.
	 *
	 * @return the titular dependente
	 */
	public List<TitularDependenteDto> getTitularDependente() {
		if (titularDependente == null)
			titularDependente = new ArrayList<>();
		return titularDependente;
	}

	/**
	 * Sets the titular dependente.
	 *
	 * @param titularDependente
	 *            the new titular dependente
	 */
	public void setTitularDependente(List<TitularDependenteDto> titularDependente) {
		this.titularDependente = titularDependente;
	}

	/**
	 * Gets the trailler sub fatura.
	 *
	 * @return the trailler sub fatura
	 */
	public List<TraillerSubFaturaDto> getTraillerSubFatura() {
		if (traillerSubFatura == null)
			traillerSubFatura = new ArrayList<>();
		return traillerSubFatura;
	}

	/**
	 * Sets the trailler sub fatura.
	 *
	 * @param traillerSubFatura
	 *            the new trailler sub fatura
	 */
	public void setTraillerSubFatura(List<TraillerSubFaturaDto> traillerSubFatura) {
		this.traillerSubFatura = traillerSubFatura;
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

	@Override
	public String toString() {
		return "HeaderSubFaturaDto [sequenciaDaFatura=" + sequenciaDaFatura + ", numeroDaSubfatura=" + numeroDaSubfatura
				+ ", nomeDaSubfatura=" + nomeDaSubfatura + ", filler=" + filler + ", titularDependente="
				+ titularDependente + ", traillerSubFatura=" + traillerSubFatura + ", traillerObservacao="
				+ traillerObservacao + "]";
	}

}
