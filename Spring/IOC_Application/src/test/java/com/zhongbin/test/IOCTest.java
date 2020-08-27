package com.zhongbin.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.bean.Book;
import com.zhongbin.dao.BookDao;

public class IOCTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		Book book = ioc.getBean(Book.class);
		BookDao bookDao = ioc.getBean(BookDao.class);
		
		System.out.println(bookDao.selectOne("cb72ad2"));
//		bookDao.add(book);
		
	}
	

}
