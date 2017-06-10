package br.com.syshealth.gestao.bradesco.commons.file.fixed.exception;

/**
 * The Class LineSizeException.
 *
 * @author Danilo.Rubervany
 */
public class LineSizeException extends FixedFormatException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1835232677021723607L;

	private int sizeInformed;

	private int totalSize;

	private String prefix;

	/**
	 * Instantiates a new line size exception.
	 */
	public LineSizeException() {
		super();
	}

	/**
	 * Instantiates a new line size exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public LineSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new line size exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public LineSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new line size exception.
	 *
	 * @param message
	 *            the message
	 */
	public LineSizeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new line size exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public LineSizeException(Throwable cause) {
		super(cause);
	}

	public LineSizeException(int sizeInformed, int totalSize, String prefix) {
		super("Linha com tamanho menor que o suportado");
		this.sizeInformed = sizeInformed;
		this.totalSize = totalSize;
		this.prefix = prefix;
	}

	public int getSizeInformed() {
		return sizeInformed;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public String getPrefix() {
		return prefix;
	}

}
