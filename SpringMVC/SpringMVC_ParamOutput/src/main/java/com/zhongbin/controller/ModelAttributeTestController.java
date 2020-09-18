package com.zhongbin.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongbin.bean.Car;

/**
 * 测试 @ModelAttribute注解
 * 	解决全字段更新的问题：如何保证只更新有变化的数据？
 * 
 * @ModelAttribute
 * 	>标在方法上：提前与目标方法运行
 * 			可以提前查出数据库中的信息并封装保存。
 * 	>标在参数上：取出在@ModelAttribute标注的方法中保存的数据
 * 
 * @author XiaoBin
 * 2020年9月17日下午4:03:06
 */

@Controller
public class ModelAttributeTestController {

	@RequestMapping("/updateCar")
	public String updateCar(@ModelAttribute("car")Car car,Map<String,Object> map,String a){
		/*
		 * Car对象的封装过程：1.创建一个Car对象，属性初始值都是null
		 * 				  2.将有的属性赋值给该对象。
		 * 	问题：更新时没有的属性为null，全字段更新会产生错误
		 * 	解决方案：不新建对象，从数据库中拿出要更新的数据并封装对象。然后调用全字段更新。(用 @ModelAttribute注解)
		 */
		
		//这里的map对象和ModelAttributeOnMethod中的model对象是同一个对象。
		System.out.println(a);
		System.out.println(car);
		Set<Entry<String,Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry);
		}
		return "success";
	}
	
	@ModelAttribute
	public void ModelAttributeOnMethod(Model model){
		//模拟生成数据库中查到的car
		Car car = new Car("Ferrari","red",999999.9f);
		
		System.out.println("数据库查到要更新的图书信息是："+car);
		//将数据库中查到的car保存起来
		model.addAttribute("car", car);
		
		System.out.println("@ModelAttribute标注的方法...");
	}
}
