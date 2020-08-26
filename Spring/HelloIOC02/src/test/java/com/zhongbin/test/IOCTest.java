package com.zhongbin.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	@Test
	public void test01() throws SQLException {
		/**
		 * 从容器中拿到连接池
		 */
		DataSource dataSource = ioc.getBean(DataSource.class);
		
		System.out.println(dataSource.getConnection());

	}
	
	@Test
	public void test02() throws Exception {
		Object user03 = ioc.getBean("user03");
		System.out.println(user03);
	}
}

