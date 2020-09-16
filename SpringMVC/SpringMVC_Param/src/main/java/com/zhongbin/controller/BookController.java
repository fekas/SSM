package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongbin.bean.Book;

@Controller
public class BookController {
	
	/**
	 * 如果我们请求的对象是POJO(eg:Book),Spring会尝试为它的每一个属性赋值并封装
	 * 支持级联封装
	 * 请求参数名和对象中的属性名一致即可
	 * 
	 * 出现乱码？
	 * 乱码的解决方案：
	 * 响应乱码：response.setContentType("text/html;charset=UTF-8");
	 * 请求乱码：
	 * 		GET请求：改server.xml配置 在8080端口处添加URLEncoding="UTF-8"
	 * 		POST请求：在第一次获取请求参数之前设置
	 * 			request.setCharactorEncoding("UTF-8");或者自己写一个filter(Spring有提供 CharacterEncodingFilter)
	 * @param book
	 * @return
	 */
	@RequestMapping("/book")
	private String addBook(Book book) {
		System.out.println("保存的图书："+book);
		return "success";
	}
	
}
