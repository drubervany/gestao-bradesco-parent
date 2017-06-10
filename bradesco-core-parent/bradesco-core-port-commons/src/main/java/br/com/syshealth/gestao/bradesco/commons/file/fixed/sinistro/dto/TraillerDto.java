package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.annotation.Trailler;

public class TraillerDto {

    @Trailler(prefix = "T", begin = 0, end = 8)
    private Integer totalDeRegistros;

    @Trailler(prefix = "T", begin = 8, end = 22)
    private Double totalDeValorPago;

    @Trailler(prefix = "T", begin = 22, end = 499)
    private String filler;

    public Integer getTotalDeRegistros() {
        return totalDeRegistros;
    }

    public Double getTotalDeValorPago() {
        return totalDeValorPago;
    }

    public String getFiller() {
        return filler;
    }

}
