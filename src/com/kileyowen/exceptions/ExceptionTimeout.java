
package com.kileyowen.exceptions;

public class ExceptionTimeout extends Exception {

	private static final long serialVersionUID = -4693733804843616944L;

	public ExceptionTimeout(final String arg0) {
		super(arg0);
	}

	public ExceptionTimeout(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ExceptionTimeout(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExceptionTimeout(final Throwable arg0) {
		super(arg0);
	}

}
