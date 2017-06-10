package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerSubFatura;

public class TraillerSubFaturaDto {

	@TraillerSubFatura(prefix = "4", begin = 0, end = 4)
	private Integer numeroDaSubfatura;

	@TraillerSubFatura(prefix = "4", begin = 4, end = 39)
	private String tipoDeLancamento;

	@TraillerSubFatura(prefix = "4", begin = 39, end = 45)
	private Integer totalDeTitularesQtd;

	@TraillerSubFatura(prefix = "4", begin = 45, end = 51)
	private Integer totalDeDependentesQtd;

	@TraillerSubFatura(prefix = "4", begin = 51, end = 57)
	private Integer totalDeSeguradosQtd;

	@TraillerSubFatura(prefix = "4", begin = 57, end = 63)
	private Integer totalDeLancamentosQtd;

	@TraillerSubFatura(prefix = "4", begin = 63, end = 78)
	private Double totLancamentosValor;

	@TraillerSubFatura(prefix = "4", begin = 78, end = 93)
	private Double parteDoSeguradoValor;

	@TraillerSubFatura(prefix = "4", begin = 93, end = 179)
	private String filler;

	public Integer getNumeroDaSubfatura() {
		return numeroDaSubfatura;
	}

	public void setNumeroDaSubfatura(Integer numeroDaSubfatura) {
		this.numeroDaSubfatura = numeroDaSubfatura;
	}

	public String getTipoDeLancamento() {
		return tipoDeLancamento;
	}

	public void setTipoDeLancamento(String tipoDeLancamento) {
		this.tipoDeLancamento = tipoDeLancamento;
	}

	public Integer getTotalDeTitularesQtd() {
		return totalDeTitularesQtd;
	}

	public void setTotalDeTitularesQtd(Integer totalDeTitularesQtd) {
		this.totalDeTitularesQtd = totalDeTitularesQtd;
	}

	public Integer getTotalDeDependentesQtd() {
		return totalDeDependentesQtd;
	}

	public void setTotalDeDependentesQtd(Integer totalDeDependentesQtd) {
		this.totalDeDependentesQtd = totalDeDependentesQtd;
	}

	public Integer getTotalDeSeguradosQtd() {
		return totalDeSeguradosQtd;
	}

	public void setTotalDeSeguradosQtd(Integer totalDeSeguradosQtd) {
		this.totalDeSeguradosQtd = totalDeSeguradosQtd;
	}

	public Integer getTotalDeLancamentosQtd() {
		return totalDeLancamentosQtd;
	}

	public void setTotalDeLancamentosQtd(Integer totalDeLancamentosQtd) {
		this.totalDeLancamentosQtd = totalDeLancamentosQtd;
	}

	public Double getTotLancamentosValor() {
		return totLancamentosValor;
	}

	public void setTotLancamentosValor(Double totLancamentosValor) {
		this.totLancamentosValor = totLancamentosValor;
	}

	public Double getParteDoSeguradoValor() {
		return parteDoSeguradoValor;
	}

	public void setParteDoSeguradoValor(Double parteDoSeguradoValor) {
		this.parteDoSeguradoValor = parteDoSeguradoValor;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public String toString() {
		return "TraillerSubFaturaDto [numeroDaSubfatura=" + numeroDaSubfatura + ", tipoDeLancamento=" + tipoDeLancamento
				+ ", totalDeTitularesQtd=" + totalDeTitularesQtd + ", totalDeDependentesQtd=" + totalDeDependentesQtd
				+ ", totalDeSeguradosQtd=" + totalDeSeguradosQtd + ", totalDeLancamentosQtd=" + totalDeLancamentosQtd
				+ ", totLancamentosValor=" + totLancamentosValor + ", parteDoSeguradoValor=" + parteDoSeguradoValor
				+ ", filler=" + filler + "]";
	}

}
