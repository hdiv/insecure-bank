package org.hdivsamples.config;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;

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

		try {
			configureLDAP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Spring context listener
		container.addListener(new RequestContextListener());

		// Spring Security Filter
		container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	}

	private void configureLDAP() throws LDAPException, URISyntaxException {
		InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=example,dc=com");
		config.addAdditionalBindCredentials("cn=admin,dc=example,dc=com", "password");
		config.setListenerConfigs(InMemoryListenerConfig.createLDAPConfig("myListener", 10389));
		InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
		ds.importFromLDIF(true, Paths.get(SpringWebInit.class.getResource("/ldap.ldif").toURI()).toFile());
		ds.startListening();
	}
}
