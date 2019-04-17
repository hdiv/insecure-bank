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

import org.springframework.security.core.context.SecurityContextHolder;

public class RequestFilter implements Filter {

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
			if (userAddr != null) {
				if (!userAddr.equals(request.getRemoteAddr())) {
					// User Ip has changed. Invalidate user and redirect to login
					SecurityContextHolder.getContext().setAuthentication(null);
				}
			}
			else {
				userAddrMap.put(username, request.getRemoteAddr());
			}

		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}

}
