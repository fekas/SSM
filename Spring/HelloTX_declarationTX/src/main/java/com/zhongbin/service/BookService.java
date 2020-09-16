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
	 * ����
	 * @param username
	 * @param isbn
	 * 
	 * @Transactional������
	 * propagation	����Ĵ�����Ϊ �Ƿ���Ҫ����һ������
	 * 		REQUIRED		�����ã������½����������Զ��̳��ڴ�����
	 * 			��֮ǰ�����connection�����������ʹ��
	 * 		REQUIRES_NEW	��Ҫһ�������������������
	 * 			��ȡһ���µ�connectionʹ��
	 * 		SUPPORTS		�����ã�����?
	 * 		NOT_SUPPORTED	�������
	 * 		MANDATORY		���������ڵ�ǰ�����ڲ����������쳣
	 * 		NEVER			��Ӧ�������������У������������������쳣
	 * 		NESTED
	 * isolation	������뼶��
	 * 		READ_UNCOMMITTED	�ɶ�����δ�ύ������
	 * 		READ_COMMITTED		ֻ�ܶ��������ύ������
	 * 		REPEATABLE_READ		����������ظ���ȡĳ�����ݣ���������λỰ�ڼ䲻���յ���ĻỰ��Ӱ��
	 * 		SERIALIZABLE
	 * 		DEFAULT
	 * readOnly
	 * timeout
	 * rollbackFor					��Щ�쳣�ع�
	 * rollbackForClassName
	 * noRollbackFor				��Щ�쳣���ع�
	 * noRollbackForClassName
	 * 
	 * һ��Ĭ������ʱ�쳣�ع�������ʱ�쳣���ع�
	 * 
	 * 
	 * mysql����̨��ѯ�Ự���뼶������:select @@tx_isolation
	 * set [session(�޸ı��λỰ)|global(�޸�ȫ��)] Transaction isolation level {READ_UNCOMMITTED|READ_COMMITTED|REPEATABLE_READ|SERIALIZABLE}
	 * mysql�����������start transaction����commit�ύ
	 */
	@Transactional(isolation=Isolation.DEFAULT)
	public void checkout(String username,String isbn){
		
		bookDao.updateCount(isbn);
		
		int price = bookDao.getPrice(isbn);
		
		bookDao.updateBalance(username, price);
	}
}
