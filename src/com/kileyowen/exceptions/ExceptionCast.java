
package com.kileyowen.exceptions;

public class ExceptionCast extends Exception {

	private static final long serialVersionUID = -5484464270678113212L;

	public ExceptionCast(final String arg0) {
		super(arg0);
	}

	public ExceptionCast(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ExceptionCast(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExceptionCast(final Throwable arg0) {
		super(arg0);
	}

}
