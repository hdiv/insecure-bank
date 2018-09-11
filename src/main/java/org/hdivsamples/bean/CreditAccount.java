package org.hdivsamples.bean;

public class CreditAccount {

	private int id;

	private String number;

	private String username;

	private String description;

	private double availableBalance;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(final double availableBalance) {
		this.availableBalance = availableBalance;
	}

}
