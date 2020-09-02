package com.zhongbin.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zhongbin.bean.Book;

public class TxTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
	NamedParameterJdbcTemplate namedParameterJdbcTemplate = ioc.getBean(NamedParameterJdbcTemplate.class);


	@Test
	public void test01() throws SQLException {
		DataSource source = ioc.getBean(DataSource.class);
		Connection connection = source.getConnection();
		System.out.println(connection);
		connection.close();
	}

	
	//��jdbcTemplate�������ݿ�
	@Test
	public void test02() throws Exception {
		String sql = "UPDATE books SET bookname = ? WHERE bookid = ?";
		int update = jdbcTemplate.update(sql,"Spring","cb72ad2");
		System.out.println(update);
	}
	//��������
	@Test
	public void test03() throws Exception {
		String sql = "INSERT INTO books(bookid,bookname,author,count,price) VALUES(?,?,?,?,?)";
		
		ArrayList<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]{"003","AAA","AQA",121,123.1});
		batchArgs.add(new Object[]{"004","BBB","QAQ",421,23.1});
		batchArgs.add(new Object[]{"005","CCC","QAA",181,13.1});
		
		int[] is = jdbcTemplate.batchUpdate(sql,batchArgs);
		
		for (int i : is) {
			System.out.println(i);
		}
	}
	//��ѯĳ������װ�ɶ���,��ѯû����ᱨ��
	@Test
	public void test04() throws Exception {
		String sql = "SELECT bookid,bookname,author,count,price FROM books WHERE bookid = ?";
		Book book = null;
		try {
			book = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class),"003");
		} catch (Exception e) {
		}
		System.out.println(book);
	}
	//��ѯ����ж��������List
	@Test
	public void test05() throws Exception {
		String sql = "SELECT bookid,bookname,author,count,price FROM books WHERE price > ?";
		
		List<Book> books = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class),50);
		
		for (Book book : books) {
			System.out.println(book);
		}
	}
	//��ѯ���ֵ
	@Test
	public void test06() throws Exception {
		String sql = "SELECT MAX(count) FROM books";
		//���۷��ص��������ǵ������ݣ�����quryForObject
		Double queryForObject = jdbcTemplate.queryForObject(sql, Double.class);
		System.out.println(queryForObject);
	}
	
	/*����������SQL��䣺
	 * 		��������ռλ��"?"�����Ǳ�����
	 * 		eg��INSERT INTO books(bookid,bookname) VALUES(:bookid,:bookname)
	 * Spring��һ��֧�־���������jdbcTemplate:
	 * ռλ��������
	 * */
	@Test
	public void test07() throws Exception {
		String sql = "INSERT INTO books"
				+ "(bookid,bookname,author,count,price) "
				+ "VALUES(:bookid,:bookname,:author,:count,:price)";
		
		Map<String,Object> paramMap = new HashMap<>();
		//�����о���������ֵ����map��
		paramMap.put("bookid", "009");
		paramMap.put("bookname", "HHH");
		paramMap.put("author", "WOWOWO");
		paramMap.put("count", 128);
		paramMap.put("price", 9.9);
		int update = namedParameterJdbcTemplate.update(sql, paramMap );
		System.out.println(update);
	}
	@Test
	public void test08() throws Exception {
		String sql = "INSERT INTO books"
				+ "(bookid,bookname,author,count,price) "
				+ "VALUES(:bookid,:bookname,:author,:count,:price)";
		Book book = new Book("YYY","001","XXX",99,45.9f);
		
		
		int update = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(book));
		System.out.println(update);
	}
}
