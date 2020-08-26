package com.zhongbin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.service.CarService;
import com.zhongbin.service.UserService;

public class IOCTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void test01() {
		CarService carService = ioc.getBean(CarService.class);
		UserService userService = ioc.getBean(UserService.class);
		
		//spring中可以用带泛型的父类类型来确定子类的类型
		System.out.println(carService.getClass().getGenericSuperclass());
		System.out.println(userService.getClass().getGenericSuperclass());
	}

}
