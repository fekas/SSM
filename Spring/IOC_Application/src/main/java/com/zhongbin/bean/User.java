package com.zhongbin.bean;

public class User {
	private String userid;
	private String username;
	private String pwd;
	private Float balance;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String pwd, Float balance, String userid) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.balance = balance;
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", pwd=" + pwd + ", balance=" + balance + ", userid=" + userid + "]";
	}
	
	
}
