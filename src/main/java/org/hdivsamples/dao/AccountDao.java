package org.hdivsamples.dao;

import java.util.List;

import org.hdivsamples.bean.Account;

public interface AccountDao {

	List<Account> findUsersByUsernameAndPassword(String username, String password);

	List<Account> findUsersByUsername(String username);

	String findAccountNameByUsername(String username);

	List<Account> findAllUsers();

}
