package org.hdiv.samples.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transfer {

	private int id;

	private String fromAccount;

	private String toAccount;

	private String description;

	private double amount;

	private double fee = 20.0;

	private String username;

	private Date date;

}
