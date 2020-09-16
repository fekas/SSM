package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	/**
	 * 在@RequestMapping中限定接受请求的方式
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/book/{bid}",method=RequestMethod.GET)
	public String getBook(@PathVariable("bid") Integer id){
		System.out.println("查询到了"+id+"到图书");
		return "success";
	}
	
	
	@RequestMapping(value="/book/{bid}",method=RequestMethod.DELETE)
	public String deleteBook(@PathVariable("bid") Integer id){
		System.out.println("删除了"+id+"到图书");
		return "success";
	}
	
	@RequestMapping(value="/book/{bid}",method=RequestMethod.PUT)
	public String updateBook(@PathVariable("bid") Integer id){
		System.out.println("更新了"+id+"到图书");
		return "success";
	}
	
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public String addBook(){
		System.out.println("添加了图书");
		return "success";
	}
}
