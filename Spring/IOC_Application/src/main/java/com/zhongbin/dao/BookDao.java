package com.zhongbin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhongbin.bean.Book;

@Repository
public class BookDao extends BaseDao<Book>{
	
	PreparedStatement prepareStatement;
	
	@Autowired
	Book book;
	
	@Override
	public void add(Book T) throws SQLException {
		Connection conn = getConn();
		String sql = "insert into books (bookid,bookname,author,count,price)"
				+ "values(?,?,?,?,?)";
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, T.getBookid());
		prepareStatement.setString(2, T.getBookname());
		prepareStatement.setString(3, T.getAuthor());
		prepareStatement.setInt(4, T.getCount());
		prepareStatement.setFloat(5, T.getPrice());
		
		prepareStatement.executeUpdate();
		
		prepareStatement.close();
		conn.close();
	}

	@Override
	public Book selectOne(String id) throws SQLException {
		
		Connection conn = getConn();
		String sql = "SELECT bookid,bookname,author,count,price "
				+ "FROM books WHERE bookid = ?";
		
		prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, id);
		
		ResultSet resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			book.setBookid(resultSet.getString(1));
			book.setBookname(resultSet.getString(2));
			book.setAuthor(resultSet.getString(3));
			book.setCount(resultSet.getInt(4));
			book.setPrice(resultSet.getFloat(5));
		}
		
		resultSet.close();
		prepareStatement.close();
		conn.close();
		return book;
	}

	@Override
	public List<Book> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String id, Book T) {
		// TODO Auto-generated method stub
		
	}

}
