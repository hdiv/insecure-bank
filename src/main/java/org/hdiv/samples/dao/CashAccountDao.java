package org.hdiv.samples.dao;

import java.util.List;

import org.hdiv.samples.bean.CashAccount;

public interface CashAccountDao {

	List<CashAccount> findCashAccountsByUsername(String username);

	double getFromAccountActualAmount(String fromAccount);

	void updateCashAccount(String fromAccount, double amountTotal);

	int getIdFromNumber(String fromAccount);

}
