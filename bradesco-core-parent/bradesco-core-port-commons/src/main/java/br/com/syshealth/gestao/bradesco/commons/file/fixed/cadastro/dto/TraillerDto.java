package br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.dto;

import br.com.syshealth.gestao.bradesco.commons.file.fixed.cadastro.annotation.Trailler;

public class TraillerDto {

    @Trailler(prefix = "4", begin = 0, end = 5)
    private Integer totalDeTitulares;

    @Trailler(prefix = "4", begin = 5, end = 10)
    private Integer totalDeDependentes;

    @Trailler(prefix = "4", begin = 10, end = 15)
    private Integer totalDeRegistros;

    @Trailler(prefix = "4", begin = 15, end = 179)
    private String filler;

    public Integer getTotalDeTitulares() {
        return totalDeTitulares;
    }

    public void setTotalDeTitulares(Integer totalDeTitulares) {
        this.totalDeTitulares = totalDeTitulares;
    }

    public Integer getTotalDeDependentes() {
        return totalDeDependentes;
    }

    public void setTotalDeDependentes(Integer totalDeDependentes) {
        this.totalDeDependentes = totalDeDependentes;
    }

    public Integer getTotalDeRegistros() {
        return totalDeRegistros;
    }

    public void setTotalDeRegistros(Integer totalDeRegistros) {
        this.totalDeRegistros = totalDeRegistros;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

}
