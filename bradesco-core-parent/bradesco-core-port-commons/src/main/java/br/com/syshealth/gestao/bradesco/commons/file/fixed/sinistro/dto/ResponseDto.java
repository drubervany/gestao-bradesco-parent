package br.com.syshealth.gestao.bradesco.commons.file.fixed.sinistro.dto;

/**
 * The Class ResponseDto.
 * 
 * @author Danilo.Rubervany
 */
public class ResponseDto {

    /** The codigo. */
    private Integer codigo;

    /** The mensagem. */
    private String mensagem;

    /** The identificador. */
    private SinistroDto fatura;

    /**
     * Instantiates a new response dto.
     */
    public ResponseDto() {

    }

    /**
     * Instantiates a new response dto.
     *
     * @param codigo
     *            the codigo
     * @param mensagem
     *            the mensagem
     * @param fatura
     *            the fatura
     */
    public ResponseDto(Integer codigo, String mensagem, SinistroDto fatura) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.fatura = fatura;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo
     *            the new codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the mensagem.
     *
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Sets the mensagem.
     *
     * @param mensagem
     *            the new mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Gets the fatura.
     *
     * @return the fatura
     */
    public SinistroDto getFatura() {
        return fatura;
    }

    /**
     * Sets the fatura.
     *
     * @param fatura
     *            the new fatura
     */
    public void setFatura(SinistroDto fatura) {
        this.fatura = fatura;
    }

}
