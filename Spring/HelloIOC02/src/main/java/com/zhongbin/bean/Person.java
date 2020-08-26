package com.zhongbin.bean;

public class Person {
	private String name;
	private String sex;
	private String email;
	
	
	
/*	public Person(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		System.out.println("两个参数的构造器哦..email");
	}
	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
		System.out.println("两个参数的构造器哦..sex");
	}*/
	public Person(String name, String sex, String email) {
		super();
		this.name = name;
		this.sex = sex;
		this.email = email;
		System.out.println("person通过有参参构造器创建了...");
	}
	public Person() {
		super();
		System.out.println("person通过无参构造器创建了...");
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", email=" + email + "]";
	}
	
	
}
