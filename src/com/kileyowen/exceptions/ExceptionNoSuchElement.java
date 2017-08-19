
package com.kileyowen.exceptions;

public class ExceptionNoSuchElement extends Exception {

	private static final long serialVersionUID = -6242046166522860640L;

	public ExceptionNoSuchElement(final String message) {
		super(message);
	}

	public ExceptionNoSuchElement(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ExceptionNoSuchElement(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionNoSuchElement(final Throwable cause) {
		super(cause);
	}

}
