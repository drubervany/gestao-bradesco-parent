package br.com.syshealth.gestao.bradesco.commons.file.fixed.exception;

public class PrefixException extends FixedFormatException {

    private static final long serialVersionUID = -4927362616289879676L;

    private String prefixCorrect;

    private String prefixIncorrect;

    private String prefix;
    private String line;

    public String getPrefixCorrect() {
        return prefixCorrect;
    }

    public void setPrefixCorrect(String prefixCorrect) {
        this.prefixCorrect = prefixCorrect;
    }

    public String getPrefixIncorrect() {
        return prefixIncorrect;
    }

    public void setPrefixIncorrect(String prefixIncorrect) {
        this.prefixIncorrect = prefixIncorrect;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
