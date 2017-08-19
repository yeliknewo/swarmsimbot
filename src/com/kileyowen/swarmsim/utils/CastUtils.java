
package com.kileyowen.swarmsim.utils;

import com.kileyowen.exceptions.ExceptionCast;
import com.kileyowen.exceptions.ExceptionNull;

public final class CastUtils {

	public static final <A, B> A cast(final Class<A> cls, final B obj) throws ExceptionCast, ExceptionNull {

		if (obj.getClass().isInstance(cls)) {

			return cls.cast(obj);

		}

		throw new ExceptionCast(NullUtils.assertNotNull(String.format("Unable to cast %s to %s", obj, cls)));

	}

}
