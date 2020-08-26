package com.zhongbin.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zhongbin.service.CarService;


/**
 * @Autowired:属性的自动注入
 * @author XiaoBin
 * 2020年8月26日上午9:34:00
 */
@Controller
public class CarServlet {
	
	//自动装配
	@Autowired
	private CarService carService;
	
	public void doGet(){
		carService.save();
	}

}
