package com.zhongbin.test;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.bean.User;
import com.zhongbin.dao.BookDao;
import com.zhongbin.dao.UserDao;

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
//		Book book = ioc.getBean(Book.class);
		User user = ioc.getBean(User.class);
//		BookDao bookDao = ioc.getBean(BookDao.class);
		UserDao userDao = ioc.getBean(UserDao.class);
		
		
//		System.out.println(bookDao.selectAll());
//		bookDao.add(book);
		
	}
	
}
