package org.hdivsamples.dao;

import org.hdivsamples.bean.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertTransfer(final Transfer transfer) {

		String sql = "INSERT INTO transfer "
				+ "(fromAccount, toAccount, description, amount, fee, username, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { transfer.getFromAccount(), transfer.getToAccount(), transfer.getDescription(),
				transfer.getAmount(), transfer.getFee(), transfer.getUsername(), transfer.getDate(), });
	}

	public void setJdbcTemplate(final JdbcTemplate paramJdbcTemplate) {
		jdbcTemplate = paramJdbcTemplate;
	}
}
