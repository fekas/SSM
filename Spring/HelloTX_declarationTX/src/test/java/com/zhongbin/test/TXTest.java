package com.zhongbin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.service.BookService;

public class TXTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	@Test
	public void test01() {
		BookService bookService = ioc.getBean(BookService.class);
		bookService.checkout("Jerry", "ISBN-001");
	}

}
