package org.hdivsamples.config;

import org.hdiv.web.multipart.HdivCommonsMultipartResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({ "org.hdivsamples.controllers" })
@EnableWebMvc
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	@Qualifier("hdivEditableValidator")
	private Validator hdivEditableValidator;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	// Multipart integration
	@Bean
	public MultipartResolver multipartResolver() {
		HdivCommonsMultipartResolver resolver = new HdivCommonsMultipartResolver();
		resolver.setMaxUploadSize(500000);
		return resolver;
	}

	// Enable Hdiv Editable field (form text and textarea) validation
	@Override
	public Validator getValidator() {
		return hdivEditableValidator;
	}
}
