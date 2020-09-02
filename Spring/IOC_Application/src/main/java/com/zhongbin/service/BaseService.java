package com.zhongbin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhongbin.dao.BaseDao;

public class BaseService<T> {
	
	@Autowired
	BaseDao<T> baseDao;
	
	public void add(T T) throws SQLException{
		baseDao.add(T);
	}
	
	public void update(String id,T T) throws SQLException {
		baseDao.update(id, T);
	}
	public void delete(String id) throws SQLException{
		baseDao.delete(id);
	}
	public T selectOne(String id) throws SQLException{
		return baseDao.selectOne(id);
	}
	public List<T> selectAll() throws SQLException{
		return baseDao.selectAll();
	}

}
