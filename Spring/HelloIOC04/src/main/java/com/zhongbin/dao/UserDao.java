package com.zhongbin.dao;

import org.springframework.stereotype.Repository;

import com.zhongbin.bean.User;

@Repository
public class UserDao extends BaseDao<User>{

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("UserDao在保存用户");
	}

}
