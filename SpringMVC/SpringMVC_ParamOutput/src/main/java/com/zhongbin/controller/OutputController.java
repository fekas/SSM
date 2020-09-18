package com.zhongbin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring把数据带回页面
 * 	>用HttpRequest等原生API
 * 	>传入Map或者Model或者ModelMap	都存放在请求域中
 * 		关系：Map、Model、ModelMap最终都是org.springframework.validation.support.BindingAwareModelMap
 * 			在工作，给BindingAwareModelMap中保存的东西都放在请求域中。
 * 			继承关系：
 * 			Map	(jdk提供的interface)				Model(Spring提供的interface)				
 * 			||									//
 * 			||	实现							   //
 * 			\/								  //
 * 		  ModelMap(Class)					 //实现
 * 						\\					//
 *						 \\	继承			   // 						
 *						  \\			  //
 * 						   ExtendedModelMap
 * 									||	继承							
 * 									\/
 * 							BindingAwareModelMap
 * 	>利用方法的返回值:ModelAndView类型返回值 	数据放在请求域中
 *	>临时给session域中保存数据
 *		1）、@SessionAttributes	只能标在类上	可能会出现异常
 *			String[] value() default {};
 *			Class<?>[] types() default {};
 *		2）、推荐用原生API
 * @author XiaoBin
 * 2020年9月17日下午3:09:19
 */
@SessionAttributes(value="QAQ",types={Integer.class})//给Map或者Model或者Model或者ModelAndView中保存的数据给session中也放一份，
								//value指定key保存,types={Integer.class}指定类型保存
@Controller
public class OutputController {

	@RequestMapping("/handle01")
	public String handle01(Map<String,Object> map) {
		map.put("QAQ", "AQA-map");
		System.out.println("Map的类型"+map.getClass());
		return "success";
	}
	@RequestMapping("/handle02")
	public String handle02(Model model) {
		model.addAttribute("QAQ","AQA-model");
		System.out.println("Model的类型"+model.getClass());
		return "success";
	}
	@RequestMapping("/handle03")
	public String handle03(ModelMap modelMap) {
		modelMap.addAttribute("QAQ","AQA-modelMap");
		System.out.println("ModelMap的类型"+modelMap.getClass());
		return "success";
	}
	
	@RequestMapping("/handle04")
	public ModelAndView handle04() {
		//原来我们返回的success是视图名，视图解析器会自动帮我们进行拼串得到页面的真是地址
		ModelAndView modelAndView = new ModelAndView("success");
		modelAndView.addObject("QAQ","AQA-modelAndView");
		return modelAndView;
	}
	@RequestMapping("/handle05")
	public String handle05(String string) {
		
		System.out.println(string);
		return "success";
	}
}
