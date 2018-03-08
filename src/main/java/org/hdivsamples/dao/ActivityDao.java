package org.hdivsamples.dao;

import java.util.Date;
import java.util.List;

import org.hdivsamples.bean.Transaction;

public interface ActivityDao {

	List<Transaction> findTransactionsByCashAccountNumber(String number);

	void insertNewActivity(Date date, String description, String number, double amount, double availablebalance);

}
