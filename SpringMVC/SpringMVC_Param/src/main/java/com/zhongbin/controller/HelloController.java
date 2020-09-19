package com.zhongbin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	private String handle01() {
		System.out.println("handle01处理hello请求");
		return "success";
	}
	
	/*
	 * 获取请求参数：
	 * 默认方式获取参数：
	 * 	直接在方法参数列表上添加与请求参数名相同的变量，用这个变量来接受参数的值，没有则为null
	 */
	@RequestMapping("/hello01")
	private String handle01(String username) {
		System.out.println("username的值是："+username);
		return "success";
	}
	
	
	/**
	 * 用 @RequestParam 注解获取请求参数
	 * String value() default "";
	 * boolean required() default true;		默认必须带有指定属性，否则报错，若修改为false则为null
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;	设置参数默认值
	 * 
	 * 与@PathVariable的区别：PathVariable获取路径上的值
	 */
	@RequestMapping("/hello02")
	private String handle02(@RequestParam(value="username", defaultValue="你没带",required=false)String user) {
		System.out.println("user的值是："+user);
		return "success";
	}
	
	/*
	 * @RequestHeader:获取请求头中某个key的值,
	 * String value() default "";
	 * boolean required() default true;	默认如果请求头中没有这个属性则报错
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;
	 * 		取代request.getHeader("User-Agent");
	 */
	@RequestMapping("/hello03")
	private String handle03(@RequestHeader("User-Agent")String userAgent) {
		System.out.println("User-Agent是："+userAgent);
		return "success";
	}
	
	/*@CookieValue 获取某个cookie的值
	 * String value() default "";
	 * boolean required() default true;
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;
	 * 	取代Cookie[] cookies = request.getCookies();
	 */
	@RequestMapping("/hello04")
	private String handle04(@CookieValue("JSESSIONID")String jId) {
		System.out.println("Cookies中的jId："+jId);
		return "success";
	}
	
	/**
	 * SpringMVC可以直接在参数上写原生API：
	 * eg:HttpServletRequest HttpSession，HttpResponse，java.security.Principal(https)
	 * Locale(国际化有关的区域对象)	InputStream(request.getInputStream();)	OutputStream(response.getOutputStream();)
	 * Reader (request.getReader();)	Writer(response.getWriter();)
	 */
	@RequestMapping("/hello05")
	private String handle05(HttpSession session,HttpServletRequest request){
		request.setAttribute("reqParam", "request域中的参数");
		session.setAttribute("sessionParam", "session域中的参数");
		return "success";
	}
}
