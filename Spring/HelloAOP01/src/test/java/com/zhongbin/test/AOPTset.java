package com.zhongbin.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongbin.impl.MyMathCalcultor;
import com.zhongbin.inter.Calculator;
import com.zhongbin.proxy.CalculatorProxy;

public class AOPTset {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");

	@Test
	public void test01() {
		MyMathCalcultor calculator = new MyMathCalcultor();
		calculator.add(3, 7);
		
		System.out.println("==============");
		//�������ִ�����㷽��
		Calculator proxy = CalculatorProxy.getProxy(calculator);
		//class com.sun.proxy.$Proxy4
		//�������ͱ��������ʵ����ͬһ���ӿڣ�������proxyҲʵ����Calculator�ӿڡ�
		
		System.out.println(proxy.getClass());
		proxy.add(2, 9);
		proxy.div(2, 0);
	}

	@Test
	public void test02() throws Exception {
		//1.��ioc�������õ�Ŀ�����ע�⣺�ýӿ����ͻ���object����û��ʵ�ֽӿ����ñ�������(cgligΪ���Ǵ����ô������)
		//��ΪAOP�ı����Ƕ�̬���������е���������Ĵ�����󣬲��Ǳ�������
		Calculator calculator = ioc.getBean(Calculator.class);
		
		int add = calculator.add(4, 9);
		System.out.println(calculator);
//		com.zhongbin.impl.MyMathCalcultor@3febb011
		System.out.println(calculator.getClass());
//		class com.sun.proxy.$Proxy13
	}
	
	@Test
	public void test03() throws Exception {
		Calculator calculator = ioc.getBean(Calculator.class);
		
		calculator.add(2, 9);
		calculator.div(4, 0);
	}
}
