
package com.kileyowen.exceptions;

public class ExceptionPageTypeMismatch extends Exception {

	private static final long serialVersionUID = -6059128818103619780L;

	public ExceptionPageTypeMismatch(final String message) {
		super(message);

	}

	public ExceptionPageTypeMismatch(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ExceptionPageTypeMismatch(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionPageTypeMismatch(final Throwable cause) {
		super(cause);
	}

}
