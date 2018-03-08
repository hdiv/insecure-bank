package org.hdivsamples.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hdivsamples.bean.Account;
import org.hdivsamples.bean.CashAccount;
import org.hdivsamples.bean.OperationConfirm;
import org.hdivsamples.bean.Transfer;
import org.hdivsamples.dao.AccountDao;
import org.hdivsamples.dao.CashAccountDao;
import org.hdivsamples.facade.TransfersFacade;
import org.hdivsamples.util.InsecureBankUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/transfer")
public class TransferController {

	private static final String PENDING_TRANSFER = "PENDING_TRANSFER";

	@Autowired
	CashAccountDao cashaccountDao;

	@Autowired
	AccountDao accountDao;

	@Autowired
	TransfersFacade transfersFacade;

	@RequestMapping
	public String newTransferForm(final Model model, final Principal principal, final HttpServletResponse response) {
		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());

		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);

		Transfer newTransfer = new Transfer();
		newTransfer.setFee(5.00);

		if (!model.containsAttribute("transfer")) {
			model.addAttribute("transfer", newTransfer);
		}
		model.addAttribute("cashAccounts", cashAccounts);
		model.addAttribute("account", account);

		response.addCookie(new Cookie("accountType", AccountType.PERSONAL));

		return "newTransfer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String transfer(@Valid @ModelAttribute final Transfer transfer, final BindingResult bindingResult, final Model model,
			final Principal principal, @CookieValue(value = "accountType", defaultValue = AccountType.PERSONAL) final String accountType,
			final HttpSession session, final HttpServletResponse response) {

		if (bindingResult.hasErrors()) {
			return newTransferForm(model, principal, response);
		}
		else {
			return AccountType.PERSONAL.equals(accountType) ? transferCheck(transfer, model, session, principal)
					: transferConfirmation(transfer, model, principal, accountType);
		}
	}

	private String transferCheck(final Transfer transfer, final Model model, final HttpSession session, final Principal principal) {
		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);
		model.addAttribute("account", account);
		model.addAttribute("transferbean", transfer);
		model.addAttribute("operationConfirm", new OperationConfirm());
		session.setAttribute(PENDING_TRANSFER, transfer);

		return "transferCheck";
	}

	private String transferConfirmation(final Transfer transfer, final Model model, final Principal principal, final String accountType) {

		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());
		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);
		double aux = transfer.getAmount();

		if (aux == 0.0) {

			model.addAttribute("transfer", transfer);
			model.addAttribute("cashAccounts", cashAccounts);
			model.addAttribute("account", account);
			model.addAttribute("error", true);

			return "newTransfer";
		}

		transfer.setUsername(principal.getName());
		transfer.setDate(new Date());

		double amount = transfer.getAmount();
		transfer.setAmount(InsecureBankUtils.round(amount, 2));

		double feeAmount = transfer.getAmount() * transfer.getFee() / 100.0;
		transfer.setFee(InsecureBankUtils.round(feeAmount, 2));

		transfersFacade.createNewTransfer(transfer);

		model.addAttribute("transferbean", transfer);
		model.addAttribute("account", account);
		model.addAttribute("accountType", accountType);

		return "transferConfirmation";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String transferCheck(final OperationConfirm operationConfirm, final BindingResult bindingResult, final Model model,
			final Principal principal, @CookieValue(value = "accountType", defaultValue = AccountType.PERSONAL) final String accountType,
			final HttpSession session) {

		Transfer transfer = (Transfer) session.getAttribute(PENDING_TRANSFER);
		session.removeAttribute(PENDING_TRANSFER);

		if ("confirm".equals(operationConfirm.getAction()) && transfer != null) {
			return transferConfirmation(transfer, model, principal, accountType);
		}
		else {
			return "redirect:/transfer";
		}
	}

	static class AccountType {
		public static final String PERSONAL = "Personal";

		public static final String BUSINESS = "Business";

	}

}