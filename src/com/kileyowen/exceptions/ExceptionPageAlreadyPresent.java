
package com.kileyowen.exceptions;

public class ExceptionPageAlreadyPresent extends Exception {

	private static final long serialVersionUID = -3679033216144609754L;

	public ExceptionPageAlreadyPresent(final String message) {
		super(message);
	}

	public ExceptionPageAlreadyPresent(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ExceptionPageAlreadyPresent(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionPageAlreadyPresent(final Throwable cause) {
		super(cause);
	}

}
