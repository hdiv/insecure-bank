package org.hdivsamples.bean;

import java.io.Serializable;

public class FileUntrustedValid implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	public FileUntrustedValid() {

	}

	public FileUntrustedValid(final String username) {

		this.username = username;
	}
}
