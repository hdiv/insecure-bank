package org.hdivsamples.util;

import com.google.common.base.Preconditions;

import java.io.File;

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
	public static String getFileExtension(String fullName) {
		Preconditions.checkNotNull(fullName);
		String fileName = (new File(fullName)).getName();
		int dotIndex = fileName.lastIndexOf('.');
		return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
	}

	public static String getNameWithoutExtension(String file) {
		Preconditions.checkNotNull(file);
		String fileName = (new File(file)).getName();
		int dotIndex = fileName.lastIndexOf('.');
		return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
	}
}
