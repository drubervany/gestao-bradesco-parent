package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.TraillerObservacao;

public class TraillerObservacaoDto {

	@TraillerObservacao(prefix = "4", begin = 0, end = 4)
	private Integer numeroSubFatura;

	@TraillerObservacao(prefix = "4", begin = 4, end = 179)
	private String observacao;

	public Integer getNumeroSubFatura() {
		return numeroSubFatura;
	}

	public void setNumeroSubFatura(Integer numeroSubFatura) {
		this.numeroSubFatura = numeroSubFatura;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "TraillerObservacaoDto [numeroSubFatura=" + numeroSubFatura + ", observacao=" + observacao + "]";
	}

}
