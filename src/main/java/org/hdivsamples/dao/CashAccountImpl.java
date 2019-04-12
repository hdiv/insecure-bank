package org.hdivsamples.dao;

import java.sql.ResultSet;
import java.util.List;

import org.hdivsamples.bean.CashAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CashAccountImpl implements CashAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CashAccount> findCashAccountsByUsername(final String username) {

		String str = "select * from cashaccount  where username=? order by number";

		RowMapper<CashAccount> rowMapper = new RowMapper<CashAccount>() {
			@Override
			public CashAccount mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				CashAccount localAccount = new CashAccount();
				localAccount.setId(paramResultSet.getInt("id"));
				localAccount.setNumber(paramResultSet.getString("number"));
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setAvailableBalance(paramResultSet.getDouble("availablebalance"));
				localAccount.setDescription(paramResultSet.getString("description"));
				return localAccount;
			}
		};
		return jdbcTemplate.query(str, new Object[] { username }, rowMapper);
	}

	@Override
	public double getFromAccountActualAmount(final String fromAccount) {

		String sql = "SELECT availablebalance FROM cashaccount WHERE number = ?";

		double id = jdbcTemplate.queryForObject(sql, new Object[] { fromAccount }, Double.class);
		return id;

	}

	@Override
	public void updateCashAccount(final String fromAccount, final double amountTotal) {

		String sql = "UPDATE cashaccount SET availablebalance= ? WHERE number = ?";

		jdbcTemplate.update(sql, new Object[] { amountTotal, fromAccount });
	}

	@Override
	public int getIdFromNumber(final String fromAccount) {

		String sql = "SELECT id FROM cashaccount WHERE number = ?";
		int id = 0;
		try {
			id = jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { fromAccount });
		}
		catch (EmptyResultDataAccessException e) {
		}
		return id;
	}

	public void setJdbcTemplate(final JdbcTemplate paramJdbcTemplate) {
		jdbcTemplate = paramJdbcTemplate;
	}

}
