package org.hdivsamples.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashAccount {

	private int id;

	private String number;

	private String username;

	private double availableBalance;

	private String description;
}
