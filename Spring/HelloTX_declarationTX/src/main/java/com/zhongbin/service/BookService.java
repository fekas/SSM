package com.zhongbin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zhongbin.dao.BookDao;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	/**
	 * 结账
	 * @param username
	 * @param isbn
	 * 
	 * @Transactional的属性
	 * propagation	事务的传播行为 是否需要共用一个事务
	 * 		REQUIRED		有则共用，无则新建。事务属性都继承于大事务
	 * 			将之前事务的connection传给这个方法使用
	 * 		REQUIRES_NEW	需要一个新事物，将别的事务挂起
	 * 			获取一条新的connection使用
	 * 		SUPPORTS		有则共用，无则?
	 * 		NOT_SUPPORTED	有则挂起
	 * 		MANDATORY		必须运行在当前事务内部，否则抛异常
	 * 		NEVER			不应该运行在事务中，有事务在运行则抛异常
	 * 		NESTED
	 * isolation	事务隔离级别
	 * 		READ_UNCOMMITTED	可读别人未提交的数据
	 * 		READ_COMMITTED		只能读别人已提交的数据
	 * 		REPEATABLE_READ		在事务可以重复读取某个数据，数据在这次会话期间不会收到别的会话的影响
	 * 		SERIALIZABLE
	 * 		DEFAULT
	 * readOnly
	 * timeout
	 * rollbackFor					那些异常回滚
	 * rollbackForClassName
	 * noRollbackFor				哪些异常不回滚
	 * noRollbackForClassName
	 * 
	 * 一般默认运行时异常回滚，编译时异常不回滚
	 * 
	 * 
	 * mysql控制台查询会话隔离级别命令:select @@tx_isolation
	 * set [session(修改本次会话)|global(修改全局)] Transaction isolation level {READ_UNCOMMITTED|READ_COMMITTED|REPEATABLE_READ|SERIALIZABLE}
	 * mysql开启事务命令：start transaction，用commit提交
	 */
	@Transactional(isolation=Isolation.DEFAULT)
	public void checkout(String username,String isbn){
		
		bookDao.updateCount(isbn);
		
		int price = bookDao.getPrice(isbn);
		
		bookDao.updateBalance(username, price);
	}
}
