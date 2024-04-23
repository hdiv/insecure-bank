package org.hdivsamples.facade;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class StorageFacadeImpl implements StorageFacade {

	@Value("classpath:/avatars/*")
	private Resource[] avatars;

	private File folder;
	private String url;


	@PostConstruct
	public void setup() throws IOException {
		folder = Files.createTempDirectory("insecure-bank").toFile();
		url = folder + File.separator;
		for (final Resource avatar : avatars) {
			final File target = new File(folder, avatar.getFilename());
			FileUtils.copyInputStreamToFile(avatar.getInputStream(), target);
		}
	}

	@PreDestroy
	public void cleanup() throws IOException {
		FileUtils.deleteDirectory(folder);
	}


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
