package com.zhongbin.bean;

public class Book {
	private String bookname;
	private String bookid;
	private String author;
	private Integer count;
	private Float price;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Book(String bookname, String bookid, String author, Integer count, Float price) {
		super();
		this.bookname = bookname;
		this.bookid = bookid;
		this.author = author;
		this.count = count;
		this.price = price;
	}


	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Book [bookname=" + bookname + ", bookid=" + bookid + ", author=" + author + ", count=" + count
				+ ", price=" + price + "]";
	}
	
}
