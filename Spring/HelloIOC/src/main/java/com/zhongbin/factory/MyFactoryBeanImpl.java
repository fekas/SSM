package com.zhongbin.factory;

import java.util.UUID;

import org.springframework.beans.factory.FactoryBean;

import com.zhongbin.bean.User;

/**
 * 实现了FactoryBean接口的类是Spring可以认识的工厂类
 * Spring会自动的调用工厂方法创建实例
 * @author XiaoBin
 * 2020年8月23日下午9:34:04
 * 
 * 1.编写一个FactoryBean的实现类
 * 2.在Spring配置文件中进行注册
 */
public class MyFactoryBeanImpl implements FactoryBean<User>{

	/*
	 * getObject()工厂方法，返回要创建的对象。
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public User getObject() throws Exception {
		System.out.println("MyFactoryBeanImpl为您创建对象");
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(UUID.randomUUID().toString());
		return user;
	}
	/*
	 * 返回创建对象的类型
	 * spring会自动调用这个方法确认创建对象的类型
	 */
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return User.class;
	}
	
	/*
	 * 是单例？
	 */
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
