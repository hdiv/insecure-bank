package org.hdivsamples.bean;

import java.util.Date;

public class Transaction {

	private int id;

	private Date date;

	private String description;

	private String number;

	private double amount;

	private double availablebalance;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public double getAvailablebalance() {
		return availablebalance;
	}

	public void setAvailablebalance(final double availablebalance) {
		this.availablebalance = availablebalance;
	}

}
