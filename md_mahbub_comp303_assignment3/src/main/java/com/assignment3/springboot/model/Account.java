package com.assignment3.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String accountNumber;
	private String accountTypeCode;
	private Integer customerId;
	private Double balance;
	private Double overDraftLimit;

	public Account( String accountNumber, String accountTypeCode, Integer customerId, Double balance, Double overDraftLimit) {

		this.accountNumber = accountNumber;
		this.accountTypeCode = accountTypeCode;
		this.customerId = customerId;
		this.balance = balance;
		this.overDraftLimit = overDraftLimit;
	}
	public Account() {

	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(Double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
}
