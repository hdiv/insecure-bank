package org.hdivsamples.dao;

import java.sql.ResultSet;
import java.util.List;

import org.hdivsamples.bean.CreditAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CreditAccountImpl implements CreditAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CreditAccount> findCreditAccountsByUsername(final String username) {

		String str = "select * from creditaccount  where username=? order by number";

		RowMapper<CreditAccount> rowMapper = new RowMapper<CreditAccount>() {
			@Override
			public CreditAccount mapRow(final ResultSet paramResultSet, final int paramInt) throws java.sql.SQLException {
				CreditAccount localAccount = new CreditAccount();
				localAccount.setId(paramResultSet.getInt("id"));
				localAccount.setNumber(paramResultSet.getString("number"));
				localAccount.setUsername(paramResultSet.getString("username"));
				localAccount.setDescription(paramResultSet.getString("description"));
				localAccount.setAvailableBalance(paramResultSet.getDouble("availablebalance"));
				return localAccount;
			}
		};
		return jdbcTemplate.query(str, new Object[] { username }, rowMapper);
	}

	@Override
	public void updateCreditAccount(final int cashAccountId, final double round) {

		String sql = "UPDATE creditAccount SET availablebalance='" + round + "' WHERE cashaccountid ='" + cashAccountId + "'";
		jdbcTemplate.update(sql);
	}

	public void setJdbcTemplate(final JdbcTemplate paramJdbcTemplate) {
		jdbcTemplate = paramJdbcTemplate;
	}

}
