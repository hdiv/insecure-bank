package org.hdivsamples.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hdivsamples.controllers.ActivityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(ActivityController.class);

	Map<String, String> userAddrMap = new ConcurrentHashMap<>();

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Principal principal = httpRequest.getUserPrincipal();

		if (principal != null) {
			String username = principal.getName();
			String userAddr = userAddrMap.get(username);
			String currentUserIP = getUserIP(httpRequest);
			if (userAddr != null) {

				if (!userAddr.equals(currentUserIP)) {
					// User Ip has changed. Invalidate user and redirect to login
					logger.error("Different user IP detected. The session will be invalidated. ");
					SecurityContextHolder.getContext().setAuthentication(null);
				}
				else if (logger.isDebugEnabled()) {
					logger.debug("Valid Ip " + currentUserIP);
				}
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug("New Ip for user:" + currentUserIP);
				}

				userAddrMap.put(username, currentUserIP);
			}

		}
		chain.doFilter(request, response);

	}

	private String getUserIP(final HttpServletRequest request) {

		if (request.getHeader("X-Forwarded-For") != null) {
			return request.getHeader("X-Forwarded-For");
		}
		else {
			return request.getRemoteAddr();
		}
	}

	@Override
	public void destroy() {
	}

}
