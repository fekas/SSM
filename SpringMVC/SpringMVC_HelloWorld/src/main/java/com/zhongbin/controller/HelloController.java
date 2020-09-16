package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.添加@Controller表示这个类是一个控制器
 * @author XiaoBin
 * 2020年9月4日上午11:39:16
 * 
 * 运行流程：
 * 	>客户端点击点解发送hello请求
 * 	>springMVC的前端控制器收到所有请求
 * 	>判断收到的请求和@RequestMapping标注的哪个方法匹配
 * 	>前端控制器找到了目标处理器类和目标方法利用反射执行该方法
 * 	>springMVC认为返回值就是跳转的页面地址
 * 	>用视图解析器拼串得到完整的地址
 * 	>springMVC转发到该页面
 * 
 * @RequestMapping：告诉SpringMVC这个方法用来处理什么请求
 * @Target({ElementType.METHOD, 	写在类上：为当前类的所有请求处理方法提供一个基准路径
 * ElementType.TYPE					写在方法上：
 * })
 * String[] value() default {};
 * RequestMethod[] method() default {};		限定请求方式(get/post等),默认全接受。
 * String[] params() default {};			限定请求参数支持"!,&&,||等" eg：params={"username"},请求必须带上名为username的参数
 * String[] headers() default {};			可以限定请求头
 * String[] consumes() default {};
 * String[] produces() default {};
 * 模糊匹配功能：
 * ?代表一个字符
 * *代表一个或多个字符或一层路径
 * **代表多层路径
 * 越精确优先级越高
 *
 */
@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String myFirstRequest() {
		System.out.println("HelloController收到hello请求");
		
	//	return "/WEB-INF/pages/success.jsp";
		//用视图解析器自动拼串：prefix+return+suffix
		return "success";
	}
	@RequestMapping("/hello*")
	public String helloMyAnt(){
		
		return "success";
		
	}
	
	//路径上还可以写占位符：占位符的语法是可以在任意路径的地方写一个{变量名}
	//路径上的占位符只能占一层路径
	@RequestMapping("/hello*/{id}")
	public String hiMyAnt(@PathVariable("id")String id){
		System.out.println(id);
		return "success";
		
	}
}
