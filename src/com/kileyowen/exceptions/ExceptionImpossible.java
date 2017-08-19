
package com.kileyowen.exceptions;

public class ExceptionImpossible extends Exception {

	private static final long serialVersionUID = -3400969918135377581L;

	public ExceptionImpossible(final String arg0) {
		super(arg0);
	}

	public ExceptionImpossible(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ExceptionImpossible(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExceptionImpossible(final Throwable arg0) {
		super(arg0);
	}

}
