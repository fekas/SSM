package com.zhongbin.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * 1.编写后置处理器的实现类
 * 2.将后置处理器在ioc中注册
 * @author XiaoBin
 * 2020年8月24日下午4:09:07
 */
public class MyBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println(beanName+"将要调用初始化方法了..."+"bean:"+bean);
		
		//返回初始化的bean
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println(beanName+"的初始化方法完成..."+"bean:"+bean);

		return bean;
	}

}
