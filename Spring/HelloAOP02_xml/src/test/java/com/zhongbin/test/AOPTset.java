package com.zhongbin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.inter.Calculator;

public class AOPTset {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");

	@Test
	public void test01() {
		Calculator calculator = ioc.getBean(Calculator.class);
		calculator.div(18, 0);
	}

	
}
