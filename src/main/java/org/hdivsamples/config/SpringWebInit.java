package org.hdivsamples.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { CoreConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(final ServletContext container) throws ServletException {

		super.onStartup(container);

		// Spring context listener
		container.addListener(new RequestContextListener());

		// Spring Security Filter
		container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	}
}
