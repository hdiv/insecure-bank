package org.hdiv.samples.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditAccount {

	private int id;

	private String number;

	private String username;

	private String description;

	private double availableBalance;

}
