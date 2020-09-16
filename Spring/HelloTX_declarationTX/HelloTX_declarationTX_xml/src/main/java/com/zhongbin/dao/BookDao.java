package com.zhongbin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 1.�����
	 */
	public void updateBalance(String username,int price){
		String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
		
		jdbcTemplate.update(sql,price,username);
		
	}
	
	/**
	 * �õ�ͼ��۸�
	 * @param isbn
	 * @return
	 */
	public int getPrice(String isbn){
		String sql = "SELECT price FROM book WHERE isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class,isbn);
	}
	
	/**
	 * ����棬Ϊ�˷���ÿ�μ�1
	 * @param isbn
	 */
	public void updateCount(String isbn){
		String sql = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
		jdbcTemplate.update(sql,isbn);
	}
}
