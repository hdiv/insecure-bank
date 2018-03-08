package org.hdivsamples.bean;

import java.io.Serializable;

public class FileUntrusted extends FileUntrustedParent implements Serializable {

	private static final long serialVersionUID = 1L;

	public FileUntrusted() {

	}

	public FileUntrusted(final String username) {

		super(username);
	}
}
