package com.zhongbin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zhongbin.inter.Calculator;
import com.zhongbin.utils.LogUtils;

/**
 * ��̬����
 * ��Calculator.java���ɴ���������
 * Object newProxyInstance
 * (ClassLoader,Calss<?>[] interface,InvocationHandler h)
 * @author XiaoBin
 * 2020��9��1������2:53:48
 */
public class CalculatorProxy {
	/**
	 * Ϊ����Ĳ������󴴽�һ����̬����
	 * @param calculator
	 * @return
	 */
	//���ڲ���ʹ�ò���Ҫ���������final���η�
	public static Calculator getProxy(final Calculator calculator){
		
		ClassLoader loader = calculator.getClass().getClassLoader();
		Class<?>[] interfaces = calculator.getClass().getInterfaces();
		
		//����ִ������������ִ��Ŀ������Ŀ�귽��
		InvocationHandler h = new InvocationHandler() {
//			Object proxy:������󣬸�jdkʹ��
//			Method method����ǰ��Ҫִ��Ŀ�����ķ���
//			Object[] args�������������ʱ��紫��Ĳ���ֵ
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
				
				//����ֵ���뷵�س�ȥ
				return result;
			}
		};
		
		//proxyΪĿ����󴴽��������
		Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, h);
		return (Calculator) proxyInstance;
	}
}
