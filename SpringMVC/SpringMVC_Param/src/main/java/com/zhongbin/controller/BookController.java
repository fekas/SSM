package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongbin.bean.Book;

@Controller
public class BookController {
	
	/**
	 * �����������Ķ�����POJO(eg:Book),Spring�᳢��Ϊ����ÿһ�����Ը�ֵ����װ
	 * ֧�ּ�����װ
	 * ����������Ͷ����е�������һ�¼���
	 * 
	 * �������룿
	 * ����Ľ��������
	 * ��Ӧ���룺response.setContentType("text/html;charset=UTF-8");
	 * �������룺
	 * 		GET���󣺸�server.xml���� ��8080�˿ڴ����URLEncoding="UTF-8"
	 * 		POST�����ڵ�һ�λ�ȡ�������֮ǰ����
	 * 			request.setCharactorEncoding("UTF-8");�����Լ�дһ��filter(Spring���ṩ CharacterEncodingFilter)
	 * @param book
	 * @return
	 */
	@RequestMapping("/book")
	private String addBook(Book book) {
		System.out.println("�����ͼ�飺"+book);
		return "success";
	}
	
}
