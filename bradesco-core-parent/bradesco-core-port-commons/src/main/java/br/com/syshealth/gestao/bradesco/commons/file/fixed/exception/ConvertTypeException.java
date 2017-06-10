package br.com.syshealth.gestao.bradesco.commons.file.fixed.exception;

/**
 * The Class ConvertTypeException.
 *
 * @author Danilo.Rubervany
 */
public class ConvertTypeException extends FixedFormatException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1953365223966018695L;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new convert type exception.
	 */
	public ConvertTypeException() {
		super();
	}

	/**
	 * Instantiates a new convert type exception.
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
	public ConvertTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new convert type exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ConvertTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new convert type exception.
	 *
	 * @param message
	 *            the message
	 */
	public ConvertTypeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new convert type exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ConvertTypeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
