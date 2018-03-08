package org.hdivsamples.dao;

import java.util.List;

import org.hdivsamples.bean.CreditAccount;

public interface CreditAccountDao {

	List<CreditAccount> findCreditAccountsByUsername(String username);

	void updateCreditAccount(int cashAccountId, double round);

}
