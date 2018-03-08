package org.hdivsamples.facade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface StorageFacade {

	boolean exists(String file);

	File load(String image);

	void save(InputStream inputStream, String name) throws IOException;

}
