package br.com.syshealth.gestao.bradesco.commons.file.fixed.exception;

/**
 * The Class FixedFormatException.
 *
 * @author Danilo.Rubervany
 */
public class FixedFormatException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3049247618491562171L;

	/** The index. */
	private int index;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new fixed format exception.
	 */
	public FixedFormatException() {
		super();
	}

	/**
	 * Instantiates a new fixed format exception.
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
	public FixedFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new fixed format exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public FixedFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new fixed format exception.
	 *
	 * @param message
	 *            the message
	 */
	public FixedFormatException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new fixed format exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public FixedFormatException(Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index
	 *            the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
