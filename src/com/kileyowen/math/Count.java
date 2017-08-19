
package com.kileyowen.math;

import com.kileyowen.exceptions.ExceptionCountParse;
import com.kileyowen.exceptions.ExceptionNull;
import com.kileyowen.swarmsim.utils.ErrorMessages;
import com.kileyowen.swarmsim.utils.NullUtils;

public class Count {

	private static final String EMPTY_STRING = "";

	private static final String REGEX_IS_SCIENTIFIC_NOTATION = "[0-9]*e[0-9]*";

	private static final String REGEX_ANY_DIGIT = "[^0-9]";

	private static final String EXPONENT = "e";

	private static final String NO = "no";

	private static final String REGEX_PARSE_SCIENTIFIC_NOTATION = "[^0-9e/.]";

	private static final Count diff(final Count a, final Count b, final long precision) {

		final long difference = b.power - a.power;
		if (difference < precision) {
			return new Count(b.mantissa * Math.pow(10, difference) - a.mantissa, difference);
		}
		return new Count(b.mantissa, difference);
	}

	public static Count parse(final long target) {

		long mantissa = target;
		long power = 0;
		while (mantissa >= 10) {
			mantissa = mantissa / 10;
			power++;
		}

		return new Count(mantissa, power);
	}

	public static Count parse(final String toParse) throws ExceptionCountParse, ExceptionNull {

		if (toParse.contains(Count.NO)) {
			return Count.parse(0);
		}

		if (!toParse.matches(Count.REGEX_IS_SCIENTIFIC_NOTATION)) {
			return Count.parse(Integer.parseInt(toParse.replaceAll(Count.REGEX_ANY_DIGIT, Count.EMPTY_STRING)));
		}

		final String scientific = toParse.replaceAll(Count.REGEX_PARSE_SCIENTIFIC_NOTATION, Count.EMPTY_STRING);

		final String[] split = scientific.split(Count.EXPONENT);

		if (split.length != 2) {
			throw new ExceptionCountParse(NullUtils.assertNotNull(String.format(ErrorMessages.UNABLE_TO_PARSE_STRING, scientific)));
		}

		return new Count(Double.parseDouble(split[0]), Long.parseLong(split[1]));
	}

	private final double mantissa;

	private final long power;

	private Count(final double newMantissa, final long newPower) {
		this.mantissa = newMantissa;
		this.power = newPower;
	}

	public Count difference(final Count other, final long precision) {

		if (this.lessThan(other)) {
			return Count.diff(this, other, precision);
		}
		return Count.diff(other, this, precision);
	}

	public boolean lessThan(final Count other) {

		return this.power < other.power || this.power == other.power && this.mantissa < other.mantissa;
	}
}
