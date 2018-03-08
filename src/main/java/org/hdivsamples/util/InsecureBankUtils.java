package org.hdivsamples.util;

public abstract class InsecureBankUtils {

	public static double round(double value, final int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
