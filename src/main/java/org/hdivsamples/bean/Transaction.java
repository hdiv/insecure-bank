package org.hdivsamples.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

	private int id;

	private Date date;

	private String description;

	private String number;

	private double amount;

	private double availablebalance;

}
