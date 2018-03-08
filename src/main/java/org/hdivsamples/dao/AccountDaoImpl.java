package org.hdivsamples.dao;

import java.sql.ResultSet;
import java.util.List;

import org.hdivsamples.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Account> findUsersByUsernameAndPassword(final String username, final String password) {

		String str = "select * from account where username='" + username + "' AND password='" + password + "'";

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
	}

	@Override
	public List<Account> findUsersByUsername(final String username) {
		String str = "select * from account where username='" + username + "'";

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
	}

	@Override
	public List<Account> findAllUsers() {

		String str = "select * from account";

		RowMapper<Account> rowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				Account localAccount = new Account();
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setName(paramResultSet.getString("name"));
				localAccount.setSurname(paramResultSet.getString("surname"));
				localAccount.setPassword(paramResultSet.getString("password"));
				return localAccount;
			}
		};

		return jdbcTemplate.query(str, rowMapper);
	}

	public void setJdbcTemplate(final JdbcTemplate paramJdbcTemplate) {
		jdbcTemplate = paramJdbcTemplate;
	}
}
