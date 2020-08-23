package com.zhongbin.factory;

import com.zhongbin.bean.User;

/**
 * 静态工厂
 * @author XiaoBin
 * 2020年8月23日下午9:05:29
 */
public class UserStaticFactory {
	public static User getUser(String name){
		System.out.println("静态工厂正在创建user对象");
		User user = new User();
		user.setName(name);
		user.setEmail(name+"@qq.com");
		user.setSex("Female");
		
		return user;
	}

}
