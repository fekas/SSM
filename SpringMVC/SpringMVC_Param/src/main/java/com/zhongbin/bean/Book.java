package com.zhongbin.bean;

public class Book {
	private String bname;
	private String bid;
	private Author author;
	private Integer stock;
	
	public Book(String bname, String bid, Author author, Integer stock) {
		super();
		this.bname = bname;
		this.bid = bid;
		this.author = author;
		this.stock = stock;
	}
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Book [bname=" + bname + ", bid=" + bid + ", author=" + author + ", stock=" + stock + "]";
	}
	
}
