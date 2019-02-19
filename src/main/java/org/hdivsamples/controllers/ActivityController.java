package org.hdivsamples.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.hdivsamples.bean.Account;
import org.hdivsamples.bean.CashAccount;
import org.hdivsamples.bean.Transaction;
import org.hdivsamples.dao.AccountDao;
import org.hdivsamples.dao.ActivityDao;
import org.hdivsamples.dao.CashAccountDao;
import org.hdivsamples.dao.CreditAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

	@Autowired
	AccountDao accountDao;

	@Autowired
	CashAccountDao cashaccountDao;

	@Autowired
	CreditAccountDao creditaccountDao;

	@Autowired
	ActivityDao activityDao;

	Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@RequestMapping
	public String activity(final Model model, final Principal principal) {

		CashAccount cashAccount = new CashAccount();
		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);
		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());

		List<Transaction> firstCashAccountTransfers = activityDao.findTransactionsByCashAccountNumber(cashAccounts.get(0).getNumber());
		List<Transaction> reverseFirstCashAccountTransfers = Lists.reverse(firstCashAccountTransfers);

		model.addAttribute("account", account);
		model.addAttribute("cashAccounts", cashAccounts);
		model.addAttribute("cashAccount", cashAccount);
		model.addAttribute("firstCashAccountTransfers", reverseFirstCashAccountTransfers);
		model.addAttribute("actualCashAccountNumber", cashAccounts.get(0).getNumber());

		return "accountActivity";
	}

	@RequestMapping("{account}/detail")
	public String viewActivityByNumber(@PathVariable("account") final String number, final Model model, final Principal principal) {

		CashAccount cashAccount = new CashAccount();
		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);
		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());

		List<Transaction> firstCashAccountTransfers = activityDao.findTransactionsByCashAccountNumber(number);
		List<Transaction> reverseFirstCashAccountTransfers = Lists.reverse(firstCashAccountTransfers);

		model.addAttribute("account", account);
		model.addAttribute("cashAccounts", cashAccounts);
		model.addAttribute("cashAccount", cashAccount);
		model.addAttribute("firstCashAccountTransfers", reverseFirstCashAccountTransfers);
		model.addAttribute("actualCashAccountNumber", number);

		return "accountActivity";
	}

	@RequestMapping("credit")
	public String viewActivityByCreditNumber(@RequestParam("number") final String number, final Model model, final Principal principal) {

		logger.info("Showing info for credit card:" + number);

		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);

		model.addAttribute("account", account);
		model.addAttribute("actualCreditCardNumber", number);

		return "creditActivity";
	}

	@RequestMapping(value = { "", "/activity", "/detail" }, method = RequestMethod.POST)
	public String changeAccount(@Valid @ModelAttribute final CashAccount cashAccount, final BindingResult bindingResult, final Model model,
			final Principal principal) {

		String postNumber = cashAccount.getNumber();

		CashAccount cashAccountNewForm = new CashAccount();
		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);
		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());

		List<Transaction> firstCashAccountTransfers = activityDao.findTransactionsByCashAccountNumber(postNumber);
		List<Transaction> reverseFirstCashAccountTransfers = Lists.reverse(firstCashAccountTransfers);

		model.addAttribute("account", account);
		model.addAttribute("cashAccounts", cashAccounts);
		model.addAttribute("cashAccount", cashAccountNewForm);
		model.addAttribute("firstCashAccountTransfers", reverseFirstCashAccountTransfers);
		model.addAttribute("actualCashAccountNumber", postNumber);

		return "accountActivity";
	}

}