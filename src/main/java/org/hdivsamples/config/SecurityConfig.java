package org.hdivsamples.config;

import org.hdivsamples.dao.AccountDao;
import org.hdivsamples.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(new CustomAuthenticationProvider(this.accountDao));
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	        .authorizeRequests()
	        	.antMatchers("/resources/**").permitAll()
	            .antMatchers("/**").authenticated()
	            .anyRequest().permitAll()
	            .and()
	        .formLogin()
	        	.loginPage("/login")
	        	.defaultSuccessUrl("/dashboard")
	        	.successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
	        	.failureUrl("/login?authenticationFailure=true")
	        	.permitAll()
	        	.and()
	        .csrf().disable()
	        .logout()
	        	.logoutUrl("/j_spring_security_logout")
	        	.logoutSuccessUrl("/login?logout=true");
    }
    
}
