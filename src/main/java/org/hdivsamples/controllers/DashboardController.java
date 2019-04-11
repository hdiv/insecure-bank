package org.hdivsamples.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.hdivsamples.bean.Account;
import org.hdivsamples.bean.CashAccount;
import org.hdivsamples.bean.CreditAccount;
import org.hdivsamples.bean.FileUntrusted;
import org.hdivsamples.bean.FileUntrustedValid;
import org.hdivsamples.dao.AccountDao;
import org.hdivsamples.dao.CashAccountDao;
import org.hdivsamples.dao.CreditAccountDao;
import org.hdivsamples.facade.StorageFacade;
import org.hdivsamples.util.InsecureBankUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	@Autowired
	AccountDao accountDao;

	@Autowired
	CashAccountDao cashaccountDao;

	@Autowired
	CreditAccountDao creditaccountDao;

	@Autowired
	StorageFacade storageFacade;

	private static final String CERT_FILE_NAME = "certTemplate.crt";

	@RequestMapping
	public String activity(final Model model, final Principal principal) {

		long init = System.currentTimeMillis();

		Account account = accountDao.findUsersByUsername(principal.getName()).get(0);

		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());
		List<CreditAccount> creditAccounts = creditaccountDao.findCreditAccountsByUsername(principal.getName());

		model.addAttribute("account", account);
		model.addAttribute("cashAccounts", cashAccounts);
		model.addAttribute("creditAccounts", creditAccounts);

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "view dashboard", end.getTime() - init);

		return "dashboard";
	}

	@RequestMapping(value = "/userDetail", method = RequestMethod.GET)
	public String userDetail(final Model model, final Principal principal, @RequestParam(value = "username") final String username) {

		long init = System.currentTimeMillis();

		Account account = accountDao.findUsersByUsername(username).get(0);
		List<CreditAccount> creditAccounts = creditaccountDao.findCreditAccountsByUsername(principal.getName());

		model.addAttribute("creditAccounts", creditAccounts);
		model.addAttribute("account", account);
		model.addAttribute("accountMalicious", account);

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "user detail", end.getTime() - init);

		return "userDetail";
	}

	@RequestMapping(value = "/userDetail/avatar", method = RequestMethod.GET)
	public void getImage(final HttpServletResponse response, @RequestParam(value = "image") final String image) {

		File file = storageFacade.exists(image) ? storageFacade.load(image) : storageFacade.load("avatar.png");
		try {
			writeResponse(new FileInputStream(file), response.getOutputStream());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/userDetail/avatar/update", method = RequestMethod.POST)
	public String updateAvatar(@RequestParam("imageFile") final MultipartFile imageFile, final Principal principal,
			final RedirectAttributes redirectAttributes) {

		long init = System.currentTimeMillis();

		if (!imageFile.isEmpty()) {
			try {
				storageFacade.save(imageFile.getInputStream(), principal.getName() + ".png");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		redirectAttributes.addAttribute("username", principal.getName());

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "update avatar", end.getTime() - init);

		return "redirect:/dashboard/userDetail";
	}

	@RequestMapping(value = "/userDetail/certificate", method = RequestMethod.POST)
	public void getCertificate(final HttpServletResponse response, final Principal principal) {

		long init = System.currentTimeMillis();

		String bdAccountName = accountDao.findAccountNameByUsername(principal.getName());
		File tmpFile = null;

		try {
			// Serialize object
			tmpFile = File.createTempFile("serial", ".ser");
			FileOutputStream fos = new FileOutputStream(tmpFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(CERT_FILE_NAME).getFile());

			oos.writeObject(new FileUntrustedValid(bdAccountName, readFileToByteArray(file)));
			oos.close();
			fos.close();

			// Write into response
			response.setHeader("Content-Disposition", "attachment; filename=\"Certificate_" + bdAccountName + "_" + ".jks\"");
			int length = writeResponse(new FileInputStream(tmpFile), response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Length", length + "");

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "download certificate", end.getTime() - init);
	}

	private static byte[] readFileToByteArray(final File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		}
		catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}

	@RequestMapping(value = "/userDetail/newcertificate", method = RequestMethod.POST)
	@ResponseBody
	public String processSimple(@RequestParam(value = "file", required = false) final MultipartFile file, final Model model)
			throws IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(file.getInputStream());
		ois.readObject();
		ois.close();

		return "<p>File '" + file.getOriginalFilename() + "' uploaded successfully</p>";
	}

	@RequestMapping(value = "/userDetail/maliciouscertificate", method = RequestMethod.POST)
	public void getMaliciousCertificate(final HttpServletResponse response, final Account account) {

		Account bdAccount = accountDao.findUsersByUsername(account.getUsername()).get(0);

		File tmpFile = null;
		try {

			// Serialize object

			tmpFile = File.createTempFile("serial", ".ser");
			FileOutputStream fos = new FileOutputStream(tmpFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new FileUntrusted(account.getName()));
			oos.close();
			fos.close();

			// Write into response

			response.setHeader("Content-Disposition", "attachment; filename=\"MaliciousCertificate" + bdAccount.getName() + "_" + ".jks\"");
			int length = writeResponse(new FileInputStream(tmpFile), response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Length", length + "");

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int writeResponse(final InputStream is, final OutputStream out) {
		int total = 0;
		try {
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = is.read(buf)) >= 0) {
				out.write(buf, 0, count);
				total += count;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			}
			catch (IOException e) {
			}
		}

		return total;
	}

	@RequestMapping(value = "/cashAccount/export", method = RequestMethod.GET)
	public void exportCashAccount(final HttpServletResponse response, final Principal principal) {

		long init = System.currentTimeMillis();

		List<CashAccount> cashAccounts = cashaccountDao.findCashAccountsByUsername(principal.getName());

		String[][] data = new String[3][cashAccounts.size()];
		int i = 0;
		for (CashAccount cashAccount : cashAccounts) {
			String[] row = new String[] { cashAccount.getNumber(), cashAccount.getDescription(),
					String.valueOf(cashAccount.getAvailableBalance()) };
			data[i] = row;
			i++;
		}

		Workbook workbook = InsecureBankUtils.export("Cash Account", new String[] { "Cash Account", "Description", "Available Balance" },
				data);

		response.setHeader("Content-disposition", "attachment;filename=CashAccount.xls");
		response.setHeader("charset", "iso-8859-1");
		response.setContentType("application/octet-stream");

		try {
			workbook.write(response.getOutputStream());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {

			try {
				workbook.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
				response.flushBuffer();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "make transfer", end.getTime() - init);
	}

	@RequestMapping(value = "/creditAccount/export", method = RequestMethod.GET)
	public void exportCreditAccount(final HttpServletResponse response, final Principal principal) {

		long init = System.currentTimeMillis();

		List<CreditAccount> creditAccounts = creditaccountDao.findCreditAccountsByUsername(principal.getName());

		String[][] data = new String[3][creditAccounts.size()];
		int i = 0;
		for (CreditAccount creditAccount : creditAccounts) {
			String[] row = new String[] { creditAccount.getNumber(), creditAccount.getDescription(),
					String.valueOf(creditAccount.getAvailableBalance()) };
			data[i] = row;
			i++;
		}

		Workbook workbook = InsecureBankUtils.export("Credit Account",
				new String[] { "Credit Account", "Description", "Available Balance" }, data);

		response.setHeader("Content-disposition", "attachment;filename=CreditAccount.xls");
		response.setHeader("charset", "iso-8859-1");
		response.setContentType("application/octet-stream");

		try {
			workbook.write(response.getOutputStream());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {

			try {
				workbook.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
				response.flushBuffer();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date end = new Date();
		InsecureBankUtils.audit(end, principal.getName(), "make transfer", end.getTime() - init);
	}
}