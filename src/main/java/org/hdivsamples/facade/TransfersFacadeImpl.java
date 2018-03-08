package org.hdivsamples.facade;

import org.hdivsamples.bean.Transfer;
import org.hdivsamples.dao.ActivityDao;
import org.hdivsamples.dao.CashAccountDao;
import org.hdivsamples.dao.CreditAccountDao;
import org.hdivsamples.dao.TransferDao;
import org.hdivsamples.util.InsecureBankUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransfersFacadeImpl implements TransfersFacade {

	@Autowired
	TransferDao transferDao;

	@Autowired
	CashAccountDao cashAccountDao;

	@Autowired
	CreditAccountDao creditAccountDao;

	@Autowired
	ActivityDao activityDaoImpl;

	@Override
	public void createNewTransfer(final Transfer transfer) {

		/*
		 * Insertar la nueva transferencia
		 */
		transferDao.insertTransfer(transfer);

		/*
		 * Actualizar tabla de CashAcount
		 */
		double actualAmount = cashAccountDao.getFromAccountActualAmount(transfer.getFromAccount());
		double amountTotal = actualAmount - (transfer.getAmount() + transfer.getFee());

		cashAccountDao.updateCashAccount(transfer.getFromAccount(), InsecureBankUtils.round(amountTotal, 2));

		double amount = actualAmount - transfer.getAmount();
		double amountWithFees = amount - transfer.getFee();

		/*
		 * Actualizar tabla de CreditAcount
		 */

		int cashAccountId = cashAccountDao.getIdFromNumber(transfer.getFromAccount());
		creditAccountDao.updateCreditAccount(cashAccountId, InsecureBankUtils.round(amountTotal, 2));

		/*
		 * Insertar nuevo movimiento
		 */
		String desc = transfer.getDescription().length() > 12 ? transfer.getDescription().substring(0, 12) : transfer.getDescription();
		activityDaoImpl.insertNewActivity(transfer.getDate(), "TRANSFER: " + desc, transfer.getFromAccount(),
				-InsecureBankUtils.round(transfer.getAmount(), 2), InsecureBankUtils.round(amount, 2));

		/*
		 * Insertar nuevo movimiento de comision
		 */
		activityDaoImpl.insertNewActivity(transfer.getDate(), "TRANSFER FEE", transfer.getFromAccount(),
				-InsecureBankUtils.round(transfer.getFee(), 2), InsecureBankUtils.round(amountWithFees, 2));

		int toCashAccountId = cashAccountDao.getIdFromNumber(transfer.getToAccount());
		if (toCashAccountId <= 0) {
			return;
		}

		/*
		 * Actualizar tabla de CreditAcount destino
		 */
		actualAmount = cashAccountDao.getFromAccountActualAmount(transfer.getToAccount());
		amountTotal = actualAmount + transfer.getAmount();
		creditAccountDao.updateCreditAccount(toCashAccountId, InsecureBankUtils.round(amountTotal, 2));

		/*
		 * Insertar nuevo movimiento en destino
		 */
		desc = transfer.getDescription();
		activityDaoImpl.insertNewActivity(transfer.getDate(), "TRANSFER: " + desc, transfer.getToAccount(),
				InsecureBankUtils.round(transfer.getAmount(), 2), InsecureBankUtils.round(amountTotal, 2));

	}

}
