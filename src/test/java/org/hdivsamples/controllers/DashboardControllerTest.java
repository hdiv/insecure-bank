package org.hdivsamples.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import org.hdivsamples.config.CoreConfig;
import org.hdivsamples.config.SpringWebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebMvcConfig.class, CoreConfig.class })
public class DashboardControllerTest {

	@Autowired
	WebApplicationContext wac;

	private MockMvc mockMvc;

	private Principal principal;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		principal = new UsernamePasswordAuthenticationToken("john", "john");
	}

	@Test
	public void userDetail() throws Exception {
		mockMvc.perform(get("/dashboard/userDetail").param("username", "john")//
				.principal(principal).accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

}
