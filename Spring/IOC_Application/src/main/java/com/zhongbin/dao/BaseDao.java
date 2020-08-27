package com.zhongbin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao<T> {
	
	@Autowired
	DataSource dataSource;
	
	public Connection getConn() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = dataSource.getConnection();
		return connection;
	}
	
	public abstract void add(T T) throws SQLException;
	public abstract T selectOne(String id) throws SQLException;
	public abstract List<T> selectAll() throws SQLException;
	public abstract void delete(String id) throws SQLException;
	public abstract void update(String id,T T) throws SQLException;

}
