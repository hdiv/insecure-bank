package org.hdivsamples.bean;

import java.io.Serializable;

public class FileUntrustedValid implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private byte[] content;

	public FileUntrustedValid() {

	}

	public FileUntrustedValid(final String username, final byte[] content) {

		this.username = username;
		this.content = content;
	}
}
