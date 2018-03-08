package org.hdivsamples.controllers;

import java.security.Principal;
import java.util.List;

import org.hdivsamples.bean.Account;
import org.hdivsamples.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AccountDao accountDao;

	@RequestMapping
	public String admin(final Model model, final Principal principal) {

		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);

		List<Account> accounts = accountDao.findAllUsers();

		model.addAttribute("account", account);
		model.addAttribute("accounts", accounts);

		return "admin";
	}

}