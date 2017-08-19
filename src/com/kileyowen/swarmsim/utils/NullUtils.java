
package com.kileyowen.swarmsim.utils;

import org.eclipse.jdt.annotation.Nullable;

import com.kileyowen.exceptions.ExceptionNull;

public class NullUtils {

	public static final <T> T assertNotNull(final @Nullable T obj) throws ExceptionNull {

		if (obj == null) {
			throw new ExceptionNull();
		}

		return obj;
	}
}
