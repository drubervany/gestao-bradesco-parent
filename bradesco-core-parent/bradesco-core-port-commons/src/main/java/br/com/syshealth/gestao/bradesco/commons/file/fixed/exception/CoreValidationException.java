package br.com.syshealth.gestao.bradesco.commons.file.fixed.exception;

/**
 * The Class CoreValidationException.
 */
public class CoreValidationException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6729412823411984589L;

    /** The message. */
    private String message;

    /**
     * Instantiates a new core validation exception.
     */
    public CoreValidationException() {
        super();
    }

    /**
     * Instantiates a new core validation exception.
     *
     * @param message the message
     * @param throwable the throwable
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public CoreValidationException(String message, Throwable throwable, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
        this.message = message;
    }

    /**
     * Instantiates a new core validation exception.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public CoreValidationException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    /**
     * Instantiates a new core validation exception.
     *
     * @param message the message
     */
    public CoreValidationException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Instantiates a new core validation exception.
     *
     * @param throwable the throwable
     */
    public CoreValidationException(Throwable throwable) {
        super(throwable);
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return message;
    }

}
