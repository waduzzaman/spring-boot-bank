package com.assignment3.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounttype")
public class AccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String accountTypeCode;
	private String accountTypeName;
	private String accountTypeDesc;

	public AccountType() {

	}
	
	public AccountType(String accountTypeCode, String accountTypeName, String accountTypeDesc) {
		super();
		this.accountTypeCode = accountTypeCode;
		this.accountTypeName = accountTypeName;
		this.accountTypeDesc = accountTypeDesc;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public String getAccountTypeDesc() {
		return accountTypeDesc;
	}
	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}

}
