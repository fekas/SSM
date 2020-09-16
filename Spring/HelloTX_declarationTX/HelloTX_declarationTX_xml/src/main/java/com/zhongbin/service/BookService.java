package com.zhongbin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongbin.dao.BookDao;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	public void checkout(String username,String isbn){
		
		bookDao.updateCount(isbn);
		
		int price = bookDao.getPrice(isbn);
		
		bookDao.updateBalance(username, price);
		
		//int i = 10/0;
	}
}
