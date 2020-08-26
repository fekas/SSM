package com.zhongbin.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhongbin.servlet.CarServlet;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)//使用spring的单元测试模块来执行标了@test注解的测试
public class IOCTest {
	
	ApplicationContext ioc = null;//new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void test01() {
		Object bean = ioc.getBean("carDao");
		System.out.println(bean.getClass());
	}
	@Test
	public void test02() throws Exception {
		CarServlet carServlet = ioc.getBean(CarServlet.class);
		carServlet.doGet();
	}
	
	/*
	 * 使用Spring的单元测试
	 * 1.导入Spring-test包
	 * 2.使用@ContextConfiguration来指定Spring配置文件的位置
	 * 3.@RunWith指定用那个驱动进行单元测试，默认为junit
	 */
	
	CarServlet carServlet;
	@Test
	private void test03() {
		// TODO Auto-generated method stub
	}


}
