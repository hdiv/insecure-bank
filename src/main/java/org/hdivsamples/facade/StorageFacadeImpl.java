package org.hdivsamples.facade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

@Service
public class StorageFacadeImpl implements StorageFacade {

	private final String url = this.getClass().getClassLoader().getResource("").getPath() + "/avatars/";

	@Override
	public boolean exists(final String fileName) {
		File file = new File(url + fileName);
		return file.exists();
	}

	@Override
	public File load(final String fileName) {
		return new File(url + fileName);
	}

	@Override
	public void save(final InputStream inputStream, final String name) throws IOException {
		File file = new File(url + name);
		Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
}
