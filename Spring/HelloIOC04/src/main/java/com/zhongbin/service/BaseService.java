package com.zhongbin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhongbin.dao.BaseDao;

public class BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;
	
	public void save(){
		baseDao.save();
	}

}
