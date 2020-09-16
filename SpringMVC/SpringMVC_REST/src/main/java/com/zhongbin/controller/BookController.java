package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	/**
	 * ��@RequestMapping���޶���������ķ�ʽ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/book/{bid}",method=RequestMethod.GET)
	public String getBook(@PathVariable("bid") Integer id){
		System.out.println("��ѯ����"+id+"��ͼ��");
		return "success";
	}
	
	
	@RequestMapping(value="/book/{bid}",method=RequestMethod.DELETE)
	public String deleteBook(@PathVariable("bid") Integer id){
		System.out.println("ɾ����"+id+"��ͼ��");
		return "success";
	}
	
	@RequestMapping(value="/book/{bid}",method=RequestMethod.PUT)
	public String updateBook(@PathVariable("bid") Integer id){
		System.out.println("������"+id+"��ͼ��");
		return "success";
	}
	
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public String addBook(){
		System.out.println("�����ͼ��");
		return "success";
	}
}
