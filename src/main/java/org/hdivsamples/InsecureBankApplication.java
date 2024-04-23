package org.hdivsamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InsecureBankApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InsecureBankApplication.class, args);
	}

}
