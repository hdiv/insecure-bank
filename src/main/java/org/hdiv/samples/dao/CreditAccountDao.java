package org.hdiv.samples.dao;

import java.util.List;

import org.hdiv.samples.bean.CreditAccount;

public interface CreditAccountDao {

	List<CreditAccount> findCreditAccountsByUsername(String username);

	void updateCreditAccount(int cashAccountId, double round);

}
