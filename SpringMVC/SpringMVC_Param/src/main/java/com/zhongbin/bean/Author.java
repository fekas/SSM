package com.zhongbin.bean;

public class Author {
	private String authorName;
	private String address;
	private Integer age;
	
	public Author(String authorName, String address, Integer age) {
		super();
		this.authorName = authorName;
		this.address = address;
		this.age = age;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Author [authorName=" + authorName + ", address=" + address + ", age=" + age + "]";
	}
	
	
}
