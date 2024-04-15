package com.example.di.sample5;

public class AccountImpl implements Account {
	private String id;
	private String name;
	private Integer balance;
	
	public AccountImpl(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public void info() {
		System.out.println("계좌번호:" + id + ", 이름:" + name + ", 잔액:" + balance);
	}

}
