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
		//代理对象执行运算方法
		Calculator proxy = CalculatorProxy.getProxy(calculator);
		//class com.sun.proxy.$Proxy4
		//代理对象和被代理对象实现了同一个接口，在这里proxy也实现了Calculator接口。
		
		System.out.println(proxy.getClass());
		proxy.add(2, 9);
		proxy.div(2, 0);
	}

	@Test
	public void test02() throws Exception {
		//1.从ioc容器中拿到目标对象。注意：用接口类型或者object，若没有实现接口则用本类类型(cglig为我们创建好代理对象)
		//因为AOP的本质是动态代理容器中的组件是它的代理对象，不是本类类型
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
