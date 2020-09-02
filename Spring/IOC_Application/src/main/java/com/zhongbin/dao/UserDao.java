package com.zhongbin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhongbin.bean.User;

@Repository
public class UserDao extends BaseDao<User>{

	PreparedStatement prepareStatement;
	
	@Autowired
	User user;
	
	@Override
	public void add(User T) throws SQLException {
		Connection conn = getConn();
		String sql = "INSERT INTO users(userid,username,pwd,balance) "
				+ "VALUES (?,?,?,?);";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, T.getUserid());
		prepareStatement.setString(2, T.getUsername());
		prepareStatement.setString(3, T.getPwd());
		prepareStatement.setFloat(4, T.getBalance());
		
		prepareStatement.executeUpdate();
		
		prepareStatement.close();
		conn.close();
	}

	@Override
	public User selectOne(String id) throws SQLException {
		Connection conn = getConn();
		String sql = "SELECT userid,username,pwd,balance "
				+ "FROM users WHERE userid = ?";
		
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, id);
		
		ResultSet resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			user.setUserid(resultSet.getString(1));
			user.setUsername(resultSet.getString(2));
			user.setPwd(resultSet.getString(3));
			user.setBalance(resultSet.getFloat(4));
		}
		
		resultSet.close();
		prepareStatement.close();
		conn.close();
		
		return user;
	}

	@Override
	public List<User> selectAll() throws SQLException {
		List<User> userList = new ArrayList<>();
		
		Connection conn = getConn();
		String sql = "SELECT bookid,bookname,author,count,price "
				+ "FROM books";
		prepareStatement = conn.prepareStatement(sql);
		
		ResultSet resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			user.setUserid(resultSet.getString(1));
			user.setUsername(resultSet.getString(2));
			user.setPwd(resultSet.getString(3));
			user.setBalance(resultSet.getFloat(4));
			userList.add(user);
		}
		
		resultSet.close();
		prepareStatement.close();
		conn.close();
		return userList;
	}

	@Override
	public void delete(String id) throws SQLException {
		Connection conn = getConn();
		String sql = "DELETE FROM users WHERE userid = ?";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, id);
		
		prepareStatement.executeUpdate();
		
		prepareStatement.close();
		conn.close();
	}

	@Override
	public void update(String id, User T) throws SQLException {
		Connection conn = getConn();
		String sql = "UPDATE books SET pwd = ? ,balance = ? WHERE userid = '?'";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, T.getPwd());
		prepareStatement.setFloat(2, T.getBalance());
		
		prepareStatement.executeUpdate();
		
		prepareStatement.close();
		conn.close();
	}

}
