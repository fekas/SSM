package com.zhongbin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zhongbin.inter.Calculator;
import com.zhongbin.utils.LogUtils;

/**
 * 动态代理
 * 帮Calculator.java生成代理对象的类
 * Object newProxyInstance
 * (ClassLoader,Calss<?>[] interface,InvocationHandler h)
 * @author XiaoBin
 * 2020年9月1日下午2:53:48
 */
public class CalculatorProxy {
	/**
	 * 为传入的参数对象创建一个动态代理
	 * @param calculator
	 * @return
	 */
	//在内部类使用参数要给参数添加final修饰符
	public static Calculator getProxy(final Calculator calculator){
		
		ClassLoader loader = calculator.getClass().getClassLoader();
		Class<?>[] interfaces = calculator.getClass().getInterfaces();
		
		//方法执行器，帮我们执行目标对象的目标方法
		InvocationHandler h = new InvocationHandler() {
//			Object proxy:代理对象，给jdk使用
//			Method method：当前将要执行目标对象的方法
//			Object[] args：这个方法调用时外界传入的参数值
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				Object result = null;
				try {
					LogUtils.logStart(method, args);
					result = method.invoke(calculator, args);
					LogUtils.logReturn(method, result);
				} catch (Exception e) {
					LogUtils.logException(method,e);
				}finally{
					LogUtils.logEnd(method);
				}
				
				//返回值必须返回出去
				return result;
			}
		};
		
		//proxy为目标对象创建代理对象
		Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, h);
		return (Calculator) proxyInstance;
	}
}
