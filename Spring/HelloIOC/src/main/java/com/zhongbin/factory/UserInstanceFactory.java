package com.zhongbin.factory;

import com.zhongbin.bean.User;

/**
 * 实例工厂
 * @author XiaoBin
 * 2020年8月23日下午9:10:26
 */
public class UserInstanceFactory {
	public User getUser(String name) {
		System.out.println("实例工厂正在创建user对象");
		User user = new User();

		user.setName(name);
		user.setEmail(name + "@qq.com");
		user.setSex("Female");

		return user;
	}
}
