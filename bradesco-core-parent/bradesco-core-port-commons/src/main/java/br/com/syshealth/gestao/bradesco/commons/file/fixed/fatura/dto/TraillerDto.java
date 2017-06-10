package br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.fatura.annotation.Trailler;

public class TraillerDto {

	@Trailler(prefix = "5", begin = 0, end = 30)
	private String mensagem;

	@Trailler(prefix = "5", begin = 30, end = 179)
	private String filler;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public String toString() {
		return "TraillerDto [mensagem=" + mensagem + ", filler=" + filler + "]";
	}

}
