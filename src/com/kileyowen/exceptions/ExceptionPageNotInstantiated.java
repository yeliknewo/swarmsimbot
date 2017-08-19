
package com.kileyowen.exceptions;

public class ExceptionPageNotInstantiated extends Exception {

	private static final long serialVersionUID = 1507950381772458462L;

	public ExceptionPageNotInstantiated(final String arg0) {
		super(arg0);
	}

	public ExceptionPageNotInstantiated(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ExceptionPageNotInstantiated(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExceptionPageNotInstantiated(final Throwable arg0) {
		super(arg0);
	}

}
