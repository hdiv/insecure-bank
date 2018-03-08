package org.hdivsamples.security;

import java.util.ArrayList;
import java.util.List;

import org.hdivsamples.bean.Account;
import org.hdivsamples.dao.AccountDao;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final AccountDao accountDao;

	public CustomAuthenticationProvider(final AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		// Editable validation for Spring security Login page.
		// The login page is not generated using Spring MVC Form tags (it is not possible with Spring Security)
		// Stop login process and show login page again
		Object aux = RequestContextHolder.getRequestAttributes().getAttribute("org.hdiv.action.EDITABLE_PARAMETER_ERROR",
				RequestAttributes.SCOPE_REQUEST);

		if (aux != null) {
			throw new BadCredentialsException("Bad Credentials");
		}

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		List<Account> listAccounts = new ArrayList<>();
		try {
			listAccounts = accountDao.findUsersByUsernameAndPassword(username.toLowerCase(), password);
		}
		catch (DataAccessException e) {
			listAccounts.clear();
		}

		if (listAccounts.isEmpty()) {
			throw new BadCredentialsException("Bad Credentials");
		}

		List<GrantedAuthority> authList = new ArrayList<>(1);

		if (username.equalsIgnoreCase("john")) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		else {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		User user = new User(listAccounts.get(0).getUsername(), listAccounts.get(0).getPassword(), authList);

		return new UsernamePasswordAuthenticationToken(user, password, authList);
	}

	@Override
	public boolean supports(final Class<?> arg0) {
		return true;
	}

}